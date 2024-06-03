package com.aurora.aurora.UI.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import com.google.auth.oauth2.UserCredentials

class UserShareViewModel: ViewModel() {
    private val _userCredentials = MutableLiveData<UserCretidentialDTO>()
    val userCredentials: MutableLiveData<UserCretidentialDTO> get() = _userCredentials

    fun updateUserCretidential(userCretidentialDTO: UserCretidentialDTO) {
        _userCredentials.value = userCretidentialDTO
    }

    fun getUserCretidentail(): UserCretidentialDTO? {
        return userCredentials.value
    }
}