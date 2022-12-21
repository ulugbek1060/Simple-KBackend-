package com.example.routes

import com.example.data.model.ApiProductRequestEntity
import com.example.data.model.ProductEntity
import com.example.data.repository.ProductsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.products() {

   val productRepository: ProductsRepository by inject()

   post("/create-product") {
      val request = try {
         call.receive<ApiProductRequestEntity>()
      } catch (e: ContentTransformationException) {
         call.respond(HttpStatusCode.BadRequest)
         return@post
      }
      val response = productRepository.createProduct(request)
      call.respond(
         status = HttpStatusCode.OK,
         message = response
      )
   }

   post("/update-products") {
      val request = try {
         call.receive<ProductEntity>()
      } catch (e: ContentTransformationException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Content was invalid!"
         )
         return@post
      }
      val response = productRepository.updateProduct(request)
      call.respond(
         status = HttpStatusCode.OK,
         message = response
      )
   }

   get("/get-all-products") {
      call.respond(
         HttpStatusCode.OK,
         message = productRepository.getAllProducts()
      )
   }

   get("/get-product-by-id") {
      val productId = call.request.queryParameters["productId"]
      call.respond(
         status = HttpStatusCode.OK,
         message = productRepository.getProductById(productId)
      )
   }

   delete("/delete-product") {
      val productId = call.request.queryParameters["productId"]
      val response = productRepository.deleteProduct(productId)
      call.respond(
         status = HttpStatusCode.OK,
         message = response
      )
   }
}