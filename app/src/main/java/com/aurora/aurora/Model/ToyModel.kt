package com.aurora.aurora.Model

data class ToyModel(
    private val id: Int,
     val toyName: String,
     val toyPrice: Double,
     val toyImage: Int,
     val toyRating: Double,
     val isLike: Boolean?,
     val toyDescription: String?
)