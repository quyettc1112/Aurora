package com.aurora.aurora.UI.Activity.PayMentActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : BaseActivity() {

    private lateinit var binding: ActivityPaymentBinding

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
        clickIntentMomo()
    }

    private fun backToCart() {
        binding.customToolbar4.onStartIconClick = {
           finish()
        }
    }
    private fun clickIntentMomo() {
        binding.btnPayment.setOnClickListener {
            // Mã QR
            val qrCode = "00020101021138620010A00000072701320006970454011899MM23327M586673350208QRIBFTTA530370454064660005802VN62190515MOMOW2W5866733580036866304DA1C"
            // Tạo URL để mở ứng dụng MoMo và chuyển đến phần chức năng Nhận Tiền
            val momoUrl = "momo://openservice/?app_scheme=app_name&action=receiveMoney&msg=$qrCode"

            // Tạo intent
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(momoUrl))
            startActivity(intent)

        }

    }
}