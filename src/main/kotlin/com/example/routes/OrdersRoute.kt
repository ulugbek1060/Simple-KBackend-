package com.example.routes

import com.example.data.model.order_entity.ApiOrderRequestEntity
import com.example.data.model.order_entity.OrderEntity
import com.example.data.repository.order_repo.OrderRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.ordersRoute() {

   val orderRepository: OrderRepository by inject()

   post("/shop/create-order") {
      try {
         val request = call.receive<ApiOrderRequestEntity>()
         val response = orderRepository.createOrder(request)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: ContentTransformationException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Object type was invalid."
         )
      }
   }

   post("/shop/update-order") {
      try {
         val request = call.receive<OrderEntity>()
         val response = orderRepository.updateOrder(request)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: ContentTransformationException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Object type was invalid."
         )
      }
   }

   delete("/shop/delete-order") {
      try {
         val orderId = call.request.queryParameters["orderId"]!!
         val response = orderRepository.deleteOrder(orderId)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: NullPointerException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Order id should not be null!"
         )
      }
   }

   get("/shop/get-order") {
      try {
         val request = call.request.queryParameters["orderId"]!!
         val response = orderRepository.getOrderById(request)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: NullPointerException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Order id should not be null!"
         )
      }
   }

   get("/shop/get-all-orders") {
      call.respond(
         status = HttpStatusCode.OK,
         message = orderRepository.getOrderList()
      )
   }
}