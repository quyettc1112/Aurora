package com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.aurora.aurora.API_Repotitory.UserAPI_Repository
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.AppConfig.CustomView.CustomDialog.ErrorDialog
import com.aurora.aurora.Common.TokenManager.TokenManager
import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import com.aurora.aurora.Model.Respone.JWTObject
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.UI.ShareViewModel.UserShareViewModel
import com.aurora.aurora.databinding.ActivityLoginScreen2Binding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivityScreen2 : BaseActivity() {

    private lateinit var binding: ActivityLoginScreen2Binding

    private val userShareViewModel : UserShareViewModel by viewModels()
    @Inject
    lateinit var userapiRepository: UserAPI_Repository

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
        getTextInputLogin()
        observeViewModel()
    }

    private fun backToLogin(){
        binding.customToolbarLogin2.onStartIconClick = {
            goBackActivity(this, LoginActivity::class.java)
        }
    }

    private fun getTextInputLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            if (checkValidNumber(email, password)) {
                userShareViewModel.updateUserCretidential(UserCretidentialDTO(email, password))
                if (userShareViewModel.userCredentials.value != null) {
                    userShareViewModel.getUserCretidentail()
                        ?.let { it1 -> callUserCretidential(it1) }
                }
            }
        }
    }

    private fun checkValidNumber(email: String, password: String): Boolean {
        var isValid = true
        // Kiểm tra email rỗng hoặc không đúng định dạng
        if (email.isEmpty()) {
            binding.edtEmail.error = "Email không được để trống"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Email không hợp lệ"
            isValid = false
        }

        // Kiểm tra password rỗng hoặc quá ngắn
        if (password.isEmpty()) {
            binding.edtPassword.error = "Mật khẩu không được để trống"
            isValid = false
        } else if (password.length < 6) {
            binding.edtPassword.error = "Mật khẩu phải có ít nhất 6 ký tự"
            isValid = false
        }

        return isValid
    }

    private fun observeViewModel() {
        userShareViewModel.userCredentials.observe(this, Observer { userCretidential ->
        })

    }

    private fun callUserCretidential(userCretidentialDTO: UserCretidentialDTO) {
        userapiRepository.getUserCretidential(userCretidentialDTO)
            .enqueue(object: retrofit2.Callback<JWTObject>{
                override fun onResponse(call: Call<JWTObject>, response: Response<JWTObject>) {
                    if (response.isSuccessful) {
                        if ( !response.body().toString().isNullOrEmpty()) {
                            val jwtObject = response.body() as JWTObject
                            userShareViewModel.updateJWTToken(jwtObject)
                            TokenManager.saveAccessToken(this@LoginActivityScreen2, userShareViewModel.getJWTToken()?.jwtToken.toString())
                            startActivity(Intent(this@LoginActivityScreen2, MainActivity::class.java))
                            finish()
                        }
                    } else {
                        if(response.code() == 404) {
                            val errorDialog = ErrorDialog(
                                this@LoginActivityScreen2,
                                errorContent = "Không tìm thấy tài khoản của bạn",
                                textButton = "Quay Lại"
                            )
                            errorDialog.show()
                        }

                        if(response.code() == 400) {
                            val errorDialog = ErrorDialog(
                                this@LoginActivityScreen2,
                                errorContent = "Sai Mật Khẩu",
                                textButton = "Quay Lại"
                            )
                            errorDialog.show()
                        }
                        Log.d("CheclResponeValue", response.code().toString())
                    }
                }
                override fun onFailure(call: Call<JWTObject>, t: Throwable) {
                    Log.d("CheclResponeValue", t.message.toString())
                }


            })


    }
}