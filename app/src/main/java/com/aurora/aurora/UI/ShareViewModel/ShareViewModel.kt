package com.aurora.aurora.UI.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.CartModel
import com.aurora.aurora.Model.RequestDTO.OrderDetail
import com.aurora.aurora.Model.RequestDTO.OrderDetailsDTO
import com.aurora.aurora.Model.ToyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(): ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<CartModel>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartModel>> get() = _cartItems

    fun addItem(cartModel: CartModel) {
        val currentList = _cartItems.value ?: mutableListOf()
        val existingItem = currentList.find { it.toyModel.id == cartModel.toyModel.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            currentList.add(cartModel)
        }
        _cartItems.value = currentList
    }

    fun removeItem(cartModel: CartModel) {
        val currentList = _cartItems.value ?: mutableListOf()
        val existingItem = currentList.find { it.id == cartModel.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity -= 1
            } else {
                currentList.remove(existingItem)
            }
        }
        _cartItems.value = currentList
    }
    fun updateCartItems(newCartItems: MutableList<CartModel>) {
        _cartItems.value = newCartItems
    }

    fun createOrderDetailsDTO(deliveryLocation: String, note: String): OrderDetailsDTO {
        val orderDetails = _cartItems.value?.map { cartModel ->
            OrderDetail(
                toyId = cartModel.toyModel.idDb,
                quantity = cartModel.quantity
            )
        } ?: listOf()
        return OrderDetailsDTO(
            deliveryLocation = deliveryLocation,
            orderDetails = orderDetails,
            note = note
        )
    }



}