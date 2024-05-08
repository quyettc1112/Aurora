package com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.databinding.ActivityLoginScreen2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivityScreen2 : BaseActivity() {

    private lateinit var binding: ActivityLoginScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backToLogin()
    }

    private fun backToLogin(){
        binding.customToolbarLogin2.onStartIconClick = {
            goBackActivity(this, LoginActivity::class.java)
        }

    }
}