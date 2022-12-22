package com.example.data.model.order_entity

data class ApiOrderListResponse(
   val message: String,
   val orders: List<OrderEntity>
)