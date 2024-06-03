package com.aurora.aurora.Model.RequestDTO

import com.google.gson.annotations.SerializedName

data class UserCretidentialDTO(
   @SerializedName("email") val email: String,
   @SerializedName("password") val password: String
)
