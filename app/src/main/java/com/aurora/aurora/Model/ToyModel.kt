package com.aurora.aurora.Model

import android.provider.ContactsContract.CommonDataKinds.Phone

data class ToyModel(
     val id: Int,
     val toyName: String,
     val toyPrice: Double,
     val toyImage: Int,
     val toyRating: Double,
     val isLike: Boolean?,
     val toyDescription: String?,
     val typePoppular: Int,
     val listImage: List<Int>,
     val categoryModel: CategoryModel,
     val sellerInfo: SellerInfoModel,
     val gender: String
) {
     class CategoryModel(
          val id: Int,
          val name: String,
          val image: Int
     )

     class SellerInfoModel(
          val id: Int,
          val name: String,
          val email: String,
          val phone: String
     )




}
