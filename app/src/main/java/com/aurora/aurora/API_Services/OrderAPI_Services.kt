package com.aurora.aurora.API_Services

import com.aurora.aurora.Model.RequestDTO.OrderDetailsDTO
import com.aurora.aurora.Model.Respone.MessageRespone
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderAPI_Services {

    @POST("/toy/create-order")
    fun createOrder(
        @Body oderDetailsDTO: OrderDetailsDTO
    ): Call<MessageRespone>
}