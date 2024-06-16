package com.aurora.aurora.Model

data class CartModel(
     val id: Int,
     val toyModel: ToyModel,
     var quantity: Int
) {
     companion object {
          private var idCounter = 0
          fun create(toyModel: ToyModel, quantity: Int): CartModel {
               return CartModel(idCounter++, toyModel, quantity)
          }
     }

}
