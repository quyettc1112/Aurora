package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
import com.aurora.aurora.UI.ShareViewModel.RegisterViewModel
import com.aurora.aurora.databinding.ActivityRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.api.gax.rpc.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding

    // Declare for Google Singin Option
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOption: GoogleSignInOptions

    private val registerViewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializedGoogle()
        backToLogin()
        showEmailInfo()
        nextToRegisterScreen2()
    }

    private fun initializedGoogle() {
        googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)
    }
    private fun backToLogin(){
        binding.customToolbar3.onStartIconClick = {
            goBackActivity(this, LoginActivity::class.java)
        }
    }
    private fun showEmailInfo() {
        googleSignInClient.signOut().addOnCompleteListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 42141)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42141) {
            if (data != null) { // Kiểm tra xem có dữ liệu trả về không
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                if (task.isSuccessful) {
                    handleSignInResult(task)
                } else {
                    Toast.makeText(this, "No email selected", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Log.d("Request Code", "Received unexpected request code: $requestCode")
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                val email = account.email
                binding.edtRegisterEmail.setText(email.toString())
            } else {
                // Trường hợp này xảy ra khi không có tài khoản nào được chọn
                Toast.makeText(this, "Login failed or no account selected", Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            // Bắt lỗi khi không có tài khoản nào được chọn hoặc có sự cố khác
            Toast.makeText(this, "Error signing in: ${e.statusCode}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun nextToRegisterScreen2() {
        binding.btnRegister.setOnClickListener {
            val email = binding.edtRegisterEmail.text.toString()
            if (isValidEmail(email)) {
                val intent = Intent(this, RegisterActivity_Screen2::class.java)
                registerViewModel.updateEmail(email)
                startActivity(intent)
            } else {
                binding.edtRegisterEmail.error = "Email không hợp lệ"
            }
        }
    }
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }




}