package com.example.data.repository.order_repo

import com.example.data.model.order_entity.ApiOrderListResponse
import com.example.data.model.order_entity.ApiOrderRequestEntity
import com.example.data.model.order_entity.ApiOrderResponse
import com.example.data.model.order_entity.OrderEntity
import org.bson.types.ObjectId

class OrderRepositoryImpl : OrderRepository {

   override val orderList: ArrayList<OrderEntity> = arrayListOf()

   override suspend fun createOrder(entity: ApiOrderRequestEntity): ApiOrderResponse {
      val newOrder = OrderEntity(
         id = ObjectId().toString(),
         amount = entity.amount,
         cartOfProducts = entity.cartOfProducts,
         dateTime = entity.dateTime
      )
      orderList.add(newOrder)
      return ApiOrderResponse(
         message = "Order Successfully created!", order = newOrder
      )
   }

   override suspend fun updateOrder(entity: OrderEntity): ApiOrderResponse {
      val existingOrder = orderList.indexOfFirst { it.id == entity.id }
      return if (existingOrder != -1) {
         val newOrder = OrderEntity(
            id = entity.id, amount = entity.amount, cartOfProducts = entity.cartOfProducts, dateTime = entity.dateTime
         )
         orderList[existingOrder] = newOrder
         ApiOrderResponse(
            message = "Order Successfully updated!", order = newOrder
         )
      } else {
         ApiOrderResponse(
            message = "Not such order!"
         )
      }
   }

   override suspend fun deleteOrder(orderId: String): String {
      val existingOrder = orderList.find { it.id == orderId }
      return if (existingOrder != null) {
         orderList.remove(existingOrder)
         "Order Successfully deleted!"
      } else {
         "No such order!"
      }
   }

   override suspend fun getOrderById(orderId: String): ApiOrderResponse {
      val existingOrder = orderList.find { it.id == orderId }
      return if (existingOrder != null) {
         ApiOrderResponse(
            message = "Oder: ${existingOrder.id}",
            order = existingOrder
         )
      } else {
         ApiOrderResponse(
            message = "No Such Order"
         )
      }
   }

   override suspend fun getOrderList(): ApiOrderListResponse {
      return ApiOrderListResponse(
         message = "All Orders.",
         orders = orderList
      )
   }
}