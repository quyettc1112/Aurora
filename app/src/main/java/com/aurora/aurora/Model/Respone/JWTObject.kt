package com.aurora.aurora.Model.Respone

import com.google.gson.annotations.SerializedName

data class JWTObject(
    @SerializedName("jwtToken") val jwtToken: String
)
