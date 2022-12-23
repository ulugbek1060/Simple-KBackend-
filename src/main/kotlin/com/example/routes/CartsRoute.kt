package com.example.routes

import com.example.data.model.cart_entity.ApiCartRequestEntity
import com.example.data.model.cart_entity.ApiCartResponse
import com.example.data.model.cart_entity.CartEntity
import com.example.data.repository.cart_repo.CartRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.cartsRoute() {

   val cartRepository: CartRepository by inject()

   post("/shop/create-cart") {
      try {
         val request =  call.receive<ApiCartRequestEntity>()
         val response = cartRepository.createCart(request)
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

   post("/shop/update-cart") {
      try {
         val request =  call.receive<CartEntity>()
         val response = cartRepository.updateCart(request)
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

   delete("/shop/delete-cart") {
      try {
         val cartId = call.request.queryParameters["cartId"]!!
         val response = cartRepository.deleteCart(cartId)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: NullPointerException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Card id should not be null!"
         )
      }
   }

   get("/shop/get-cart") {
      try {
         val cartId = call.request.queryParameters["cartId"]!!
         val response = cartRepository.getCartById(cartId)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: NullPointerException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Card id should not be null!"
         )
      }
   }

   get("/shop/get-all-carts") {
      call.respond(
         status = HttpStatusCode.OK,
         message = cartRepository.getCartList()
      )
   }
}