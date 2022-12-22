package com.example.data.model.order_entity

import com.example.data.model.cart_entity.CartEntity

data class ApiOrderRequestEntity(
   val amount: Double,
   val cartOfProducts: List<CartEntity>,
   val dateTime: String
)