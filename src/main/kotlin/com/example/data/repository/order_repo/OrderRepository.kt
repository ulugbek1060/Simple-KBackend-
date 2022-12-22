package com.example.data.repository.order_repo

import com.example.data.model.order_entity.ApiOrderListResponse
import com.example.data.model.order_entity.ApiOrderRequestEntity
import com.example.data.model.order_entity.ApiOrderResponse
import com.example.data.model.order_entity.OrderEntity

interface OrderRepository {

   val orderList: ArrayList<OrderEntity>

   suspend fun createOrder(entity: ApiOrderRequestEntity): ApiOrderResponse

   suspend fun updateOrder(entity: OrderEntity): ApiOrderResponse

   suspend fun deleteOrder(orderId: String): String

   suspend fun getOrderById(orderId: String): ApiOrderResponse

   suspend fun getOrderList(): ApiOrderListResponse
}