package com.aurora.aurora.UI.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import com.aurora.aurora.Model.Respone.JWTObject
import com.google.auth.oauth2.UserCredentials

class UserShareViewModel: ViewModel() {
    private val _userCredentials = MutableLiveData<UserCretidentialDTO>()
    val userCredentials: MutableLiveData<UserCretidentialDTO> get() = _userCredentials



    private  val _jwtToken = MutableLiveData<JWTObject?>()
    val jwtToken: MutableLiveData<JWTObject?> get() = _jwtToken


    // User Cretidentail
    fun updateUserCretidential(userCretidentialDTO: UserCretidentialDTO) {
        _userCredentials.value = userCretidentialDTO
    }

    fun getUserCretidentail(): UserCretidentialDTO? {
        return userCredentials.value
    }

    // User JWT

    fun updateJWTToken(jwtObject: JWTObject) {
        _jwtToken.value = jwtObject

    }

    fun getJWTToken(): JWTObject? {
        return jwtToken.value
    }

    // Function to clear JWT Token
    fun clearJWTToken() {
        _jwtToken.value = null
    }




}