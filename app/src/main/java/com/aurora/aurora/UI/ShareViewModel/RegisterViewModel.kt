package com.aurora.aurora.UI.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.CartModel
import com.aurora.aurora.Model.RequestDTO.RegisterRequestDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel@Inject constructor(): ViewModel()  {
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>();
    private val _phone = MutableLiveData<String>();
    private val _name = MutableLiveData<String>();
    private val _gender = MutableLiveData<Boolean>();

    val email: LiveData<String> get() = _email
    val password: LiveData<String> get() = _password
    val phone: LiveData<String> get() = _phone
    val name: LiveData<String> get() = _name
    val gender: LiveData<Boolean> get() = _gender


    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updatePhone(newPhone: String) {
        val formattedPhone = formatPhoneNumber(newPhone)
        if (formattedPhone != null) {
            _phone.value = formattedPhone!!
        }
    }
    private fun formatPhoneNumber(phone: String): String? {
        return if (phone.length == 10 && phone.startsWith("0")) {
            "+84${phone.substring(1)}"
        } else {
            null
        }
    }

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateGender(newGender: Boolean) {
        _gender.value = newGender
    }
    fun getRegisterDTO(): RegisterRequestDTO{
        val registerRequestDTO = RegisterRequestDTO(
            email = email.value.toString(),
            password = password.value.toString(),
            name = name.value.toString(),
            phoneNumber =  phone.value.toString(),
            gender = gender.value!!
        )
        return registerRequestDTO
    }
    fun clearAllFields() {
        _email.value = ""
        _password.value = ""
        _phone.value = ""
        _name.value = ""
        _gender.value = false
    }




}