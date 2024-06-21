package com.aurora.aurora.Model.RequestDTO

import com.google.gson.annotations.SerializedName

data class OrderDetailsDTO(
    @SerializedName("deliveryLocation")  val deliveryLocation: String,
    @SerializedName("orderDetails") val orderDetails: List<OrderDetail>,
    @SerializedName("note") val note: String
)

data class OrderDetail(
    @SerializedName("toyId")   val toyId: String,
    @SerializedName("quantity")   val quantity: Int
)