package com.aurora.aurora.UI.Activity.PayMentActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityDonePayment2Binding
import com.aurora.aurora.databinding.ActivityDonePaymentBinding
import com.aurora.aurora.databinding.ActivityProductDetailBinding

class DonePaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonePayment2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDonePayment2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // back to main
        binding.customToolbar5.onStartIconClick = {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}