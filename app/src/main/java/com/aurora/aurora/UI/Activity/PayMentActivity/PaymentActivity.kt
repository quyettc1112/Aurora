package com.aurora.aurora.UI.Activity.PayMentActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.API_Repotitory.OrderAPI_Repository
import com.aurora.aurora.API_Repotitory.UserAPI_Repository
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.TokenManager.TokenManager
import com.aurora.aurora.Model.RequestDTO.OrderDetailsDTO
import com.aurora.aurora.Model.Respone.MessageRespone
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityPaymentBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class PaymentActivity : BaseActivity() {

    private lateinit var binding: ActivityPaymentBinding

    @Inject
    lateinit var orderapiRepository: OrderAPI_Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backToCart()
        clickIntentPaymentMethod()

        handlePaymetnMothod()

        binding.edtRegisterPhone.setText(TokenManager.getPhoneNumber(this@PaymentActivity).toString())

    }

    private fun handlePaymetnMothod() {
        binding.btnMomoPayment.setOnClickListener {
            binding.spinnerPaymentMethod.setSelection(0)
        }

        binding.btnNormalPayment.setOnClickListener {
            binding.spinnerPaymentMethod.setSelection(1)
        }

        binding.spinnerPaymentMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // You can add additional behavior if needed when the spinner selection changes
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun backToCart() {
        binding.customToolbar4.onStartIconClick = {
           finish()
        }
    }
    private fun clickIntentPaymentMethod() {
        binding.btnPayment.setOnClickListener {
            // Mã QR
            if (!binding.edtLocation.text.isNullOrEmpty()) {
                val orderDetailsDTO: OrderDetailsDTO? = intent.getParcelableExtra("orderDetails")
                Log.d("CheckDTO", orderDetailsDTO.toString())
                orderDetailsDTO?.deliveryLocation = binding.edtLocation.text.toString()
                Log.d("CheckDTO", orderDetailsDTO.toString())
                if(orderDetailsDTO != null) {
                    callCreateOrder(orderDetailsDTO)
                }
            } else binding.edtLocation.error = "Vui Lòng Nhập Địa Chỉ Giao Hàng"
        }
    }


    private fun callCreateOrder(orderDetailsDTO: OrderDetailsDTO) {
        orderapiRepository.createOrder(orderDetailsDTO).enqueue(object : Callback<MessageRespone> {
            override fun onResponse(
                call: Call<MessageRespone>,
                response: Response<MessageRespone>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { messageResponse ->

                        if ( binding.spinnerPaymentMethod.selectedItemPosition == 0) {
                            val qrCode = "00020101021138620010A00000072701320006970454011899MM23327M586673350208QRIBFTTA530370454064660005802VN62190515MOMOW2W5866733580036866304DA1C"
                            // Tạo URL để mở ứng dụng MoMo và chuyển đến phần chức năng Nhận Tiền
                            val momoUrl = "momo://openservice/?app_scheme=app_name&action=receiveMoney&msg=$qrCode"
                            // Tạo intent
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(momoUrl))
                            startActivity(intent)
                        } else {
                            startActivity(Intent(this@PaymentActivity, DonePaymentActivity::class.java))
                        }



                    } ?: run {
                        // Handle the case when the response body is null
                        showToast("Response body is null")
                    }
                } else {
                    // Handle the case when the response is not successful
                    showToast("Failed to create order: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MessageRespone>, t: Throwable) {
                // Handle the failure
                showToast("Failed to create order: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        startActivity(Intent(this@PaymentActivity, DonePaymentActivity::class.java))
        finish()
    }

}