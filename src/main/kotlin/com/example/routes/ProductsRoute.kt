package com.example.routes

import com.example.data.model.product_entity.ApiProductRequestEntity
import com.example.data.model.product_entity.ProductEntity
import com.example.data.repository.product_repo.ProductsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import org.koin.ktor.ext.inject


fun Route.productsRoute() {

   val productRepository: ProductsRepository by inject()

   post("/shop/create-product") {
      delay(1000)
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

   post("/shop/update-product") {
      delay(1000)
      try {
         val request = call.receive<ProductEntity>()
         val response = productRepository.updateProduct(request)
         call.respond(
            status = HttpStatusCode.OK,
            message = response
         )
      } catch (e: ContentTransformationException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Content was invalid!"
         )
      }
   }

   get("/shop/get-all-products") {
      delay(1000)
      call.respond(
         HttpStatusCode.OK,
         message = productRepository.getAllProducts()
      )
   }

   get("/shop/get-product") {
      delay(1000)
      try {
         val productId = call.request.queryParameters["productId"]!!
         call.respond(
            status = HttpStatusCode.OK,
            message = productRepository.getProductById(productId)
         )
      } catch (e: NullPointerException) {
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Product id should not be null!"
         )
      }
   }

   delete("/shop/delete-product") {
      delay(1000)
      try {
         val productId = call.request.queryParameters["productId"]!!
         call.respond(
            status = HttpStatusCode.OK,
            message = productRepository.deleteProduct(productId)
         )
      }catch (e:NullPointerException){
         call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Product id should not be null!"
         )
      }
   }
}