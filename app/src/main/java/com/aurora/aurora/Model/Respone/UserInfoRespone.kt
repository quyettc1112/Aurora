package com.aurora.aurora.Model.Respone

import com.google.gson.annotations.SerializedName

data class UserInfoRespone(
    @SerializedName("accountId") val accountId: String,
    @SerializedName("avatarUrl") val avatarUrl: String?,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("username") val username: String?
)
