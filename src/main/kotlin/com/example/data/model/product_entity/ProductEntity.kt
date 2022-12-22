package com.example.data.model.product_entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class ProductEntity(
   @BsonId val id: String = ObjectId().toString(),
   val title: String,
   val description: String,
   val price: Double,
   val imageUrl: String,
   val isFavorite: Boolean
)