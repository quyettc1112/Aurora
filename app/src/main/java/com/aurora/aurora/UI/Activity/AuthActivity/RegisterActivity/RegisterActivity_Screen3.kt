package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.API_Repotitory.UserAPI_Repository
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.AppConfig.CustomView.CustomDialog.ErrorDialog
import com.aurora.aurora.Common.CommonAdapter.MessageAdapter
import com.aurora.aurora.Model.Respone.ErrorResponse
import com.aurora.aurora.Model.Respone.MessageRespone
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.UI.ShareViewModel.RegisterViewModel
import com.aurora.aurora.databinding.ActivityRegisterScreen3Binding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity_Screen3 : BaseActivity() {
    private lateinit var binding: ActivityRegisterScreen3Binding
    private val registerViewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var userapiRepository: UserAPI_Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        inputValue()
    }

    private fun inputValue() {
        binding.btnRegisterScreen3.setOnClickListener {
            if (checkFieldsNotNullOrEmpty() == true && checkPhoneNumber(binding.edtRegisterPhone.text.toString())) {
                val email = intent.getStringExtra("intent_email")
                val password = intent.getStringExtra("intent_password")

                registerViewModel.updateEmail(email.toString())
                registerViewModel.updatePassword(password.toString())
                registerViewModel.updatePhone(binding.edtRegisterPhone.text.toString())
                registerViewModel.updateName(binding.edtRegisterName.text.toString())
                if (binding.edtRegisterGender.text.equals("Name")) {
                    registerViewModel.updateGender(true)
                } else registerViewModel.updateGender(false)

                callRegister()
              /*  val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()*/
                Log.d("CheckValueRegisterDTO", registerViewModel.getRegisterDTO().toString())
            }
        }

        binding.customToolbarScreen3.onStartIconClick = {
            val intent = Intent(this, RegisterActivity_Screen2::class.java)
            startActivity(intent)
            finish()
        }

        binding.edtRegisterDob.setOnClickListener {
            showDatePickerDialog()
        }

        binding.edtRegisterGender.setOnClickListener {
            showGenderSelectionDialog()
        }
    }

    private fun checkFieldsNotNullOrEmpty(): Boolean {
        val name = binding.edtRegisterName.text.toString()
        val gender = binding.edtRegisterGender.text.toString()
        val dob = binding.edtRegisterDob.text.toString()
        val phone = binding.edtRegisterPhone.text.toString()

        return when {
            name.isEmpty() -> {
                binding.edtRegisterName.error = "Tên không được để trống"
                false
            }
            gender.isEmpty() -> {
                binding.edtRegisterGender.error = "Giới tính không được để trống"
                false
            }
            dob.isEmpty() -> {
                binding.edtRegisterDob.error = "Ngày sinh không được để trống"
                false
            }
            phone.isNullOrEmpty() -> {
                binding.edtRegisterPhone.error = "Số điện thoại không được để trống"
                false
            }
            else -> true
        }
    }

    private fun showDatePickerDialog() {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a date picker dialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format the date and set it to the EditText
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.edtRegisterDob.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showGenderSelectionDialog() {
        // Các tùy chọn giới tính
        val genderOptions = arrayOf("Nam", "Nữ", "Khác")

        // Tạo dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn Giới Tính")
        builder.setItems(genderOptions) { dialog, which ->
            // Thiết lập giá trị đã chọn vào EditText
            binding.edtRegisterGender.setText(genderOptions[which])
        }

        // Hiển thị dialog
        builder.show()
    }

    fun checkPhoneNumber(phone: String): Boolean {
        return if (phone.length == 10) {
            true
        } else {
            binding.edtRegisterPhone.error = "Nhập Số Điện Thoại Hợp Lệ"
            false
        }
    }

    fun callRegister() {
        userapiRepository.callRegister(
            registerViewModel.getRegisterDTO()
        ).enqueue(object : Callback<MessageRespone> {
            override fun onResponse(
                call: Call<MessageRespone>,
                response: Response<MessageRespone>
            ) {
                if (response.isSuccessful) {
                    val message = response.body() as MessageRespone
                    Log.d("checkRespone", message.message.toString())
                    startActivity(Intent(this@RegisterActivity_Screen3, MainActivity::class.java))
                    finish()
                } else {
                    Log.d("checkRespone", response.code().toString())
                    val errorBody = response.errorBody()?.string()
                    errorBody?.let {
                        try {
                            val gson = GsonBuilder()
                                .registerTypeAdapter(List::class.java, MessageAdapter())
                                .create()
                            val errorResponse = gson.fromJson(it, ErrorResponse::class.java)
                            Log.d("checkRespone", errorResponse.message.toString())
                            val errorDialog = ErrorDialog(
                                this@RegisterActivity_Screen3,
                                errorContent = "Message: ${errorResponse.message.joinToString("\n")} \n",
                                textButton = "Quay Lại"
                            )
                            errorDialog.show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.d("checkRespone", "Lỗi khi chuyển đổi errorBody")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MessageRespone>, t: Throwable) {
                Log.d("checkRespone", t.message.toString())
            }
        })
    }

}