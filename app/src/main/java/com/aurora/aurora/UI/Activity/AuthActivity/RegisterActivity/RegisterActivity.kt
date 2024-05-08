package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
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
        googleSignInClient.signOut()
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, 42141);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42141) { // Check if the requestCode matches the one used to start the activity
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
        } else {
            Log.d("Request Code", "Received unexpected request code: $requestCode")
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(
                ApiException::class.java
            )
            if (account != null) {
                val email = account.email
                binding.edtRegisterEmail.setText(email.toString())
            }
        } catch (e: ApiException) {
            Log.w("CheckResult", "signInResult:failed code=" + e.statusCode)
        }
    }




}