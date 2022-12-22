package com.example.data.model.cart_entity

import org.bson.codecs.pojo.annotations.BsonId

data class CartEntity(
   @BsonId val id: String,
   val title: String,
   val quantity: String,
   val price: Double
)
