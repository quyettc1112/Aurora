package com.aurora.aurora.Model.RequestDTO

import com.google.gson.annotations.SerializedName

data class RegisterRequestDTO(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("gender") val gender: Boolean
)
