package com.aurora.aurora.UI.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.CartModel
import com.aurora.aurora.Model.ToyModel

class ShareViewModel: ViewModel() {
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



}