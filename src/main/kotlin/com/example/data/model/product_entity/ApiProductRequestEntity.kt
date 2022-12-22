package com.example.data.model.product_entity

data class ApiProductRequestEntity(
   val title: String,
   val description: String,
   val price: Double,
   val imageUrl: String,
   val isFavorite: Boolean
)