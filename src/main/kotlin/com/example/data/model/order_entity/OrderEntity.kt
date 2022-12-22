package com.example.data.model.order_entity

import com.example.data.model.cart_entity.CartEntity
import org.bson.codecs.pojo.annotations.BsonId

data class OrderEntity(
   @BsonId val id: String,
   val amount: Double,
   val cartOfProducts: List<CartEntity>,
   val dateTime: String
)