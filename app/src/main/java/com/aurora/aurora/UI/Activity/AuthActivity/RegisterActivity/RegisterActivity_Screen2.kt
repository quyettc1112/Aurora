package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.databinding.ActivityRegisterScreen2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity_Screen2 : BaseActivity() {

    private lateinit var binding: ActivityRegisterScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.customToolbarScreen2.onStartIconClick = {
            val intent  = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegisterScreen2.setOnClickListener {
            val intent  = Intent(this, RegisterActivity_Screen3::class.java)
            startActivity(intent)
        }
    }
}