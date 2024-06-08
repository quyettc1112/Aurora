package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.UI.ShareViewModel.RegisterViewModel
import com.aurora.aurora.databinding.ActivityRegisterScreen2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity_Screen2 : BaseActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()

    private var isPasswordMatched = false
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
            if (isPasswordMatched == false) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val intent  = Intent(this, RegisterActivity_Screen3::class.java)
                registerViewModel.updatePassword(binding.edtRegisterPasswordConfirm.toString().toString())
                startActivity(intent)
            }
        }
        binding.edtRegisterPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = binding.edtRegisterPassword.text.toString()
                val confirmPassword = s.toString()
                checkPasswordsMatch(password, confirmPassword)
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    fun checkPasswordsMatch(password: String, confirmPassword: String) {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+=!])(?=\\S+\$).{8,}\$"
        val passwordMatcher = Regex(passwordPattern)

        if (!passwordMatcher.matches(password)) {
            binding.edtRegisterPassword.error = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm ít nhất 1 ký tự in hoa, 1 số và 1 ký tự đặc biệt"
            isPasswordMatched = false
            return
        }
        if (password == confirmPassword) {
            isPasswordMatched = true
            binding.edtRegisterPasswordConfirm.setBackgroundResource(R.drawable.background_custome_input)
        } else {
            isPasswordMatched = false
            binding.edtRegisterPasswordConfirm.setBackgroundResource(R.drawable.background_custome_input_error)
        }
    }




}