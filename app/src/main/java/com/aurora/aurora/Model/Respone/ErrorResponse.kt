package com.aurora.aurora.Model.Respone

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
   @SerializedName("message") val message: List<String>,
   @SerializedName("error") val error: String,
   @SerializedName("statusCode")  val statusCode: Int
)