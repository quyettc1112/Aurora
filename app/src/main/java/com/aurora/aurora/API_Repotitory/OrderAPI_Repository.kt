package com.aurora.aurora.API_Repotitory

import com.aurora.aurora.API_Services.OrderAPI_Services
import com.aurora.aurora.API_Services.UserAPI_Service
import com.aurora.aurora.Model.RequestDTO.OrderDetailsDTO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class OrderAPI_Repository @Inject constructor(
    private val orderapiServices: OrderAPI_Services
) {
    fun createOrder(
        authorization: String,
        orderDetailsDTO: OrderDetailsDTO
    ) = orderapiServices.createOrder(authorization, orderDetailsDTO)


}