package com.example.data.repository

import com.example.data.model.ApiProductListResponse
import com.example.data.model.ApiProductRequestEntity
import com.example.data.model.ApiProductResponse
import com.example.data.model.ProductEntity
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class ProductsRepositoryImpl : ProductsRepository {

   override val productList: ArrayList<ProductEntity> = arrayListOf()

   override suspend fun createProduct(entity: ApiProductRequestEntity): ApiProductResponse {
      val newProduct = ProductEntity(
         id = ObjectId().toString(),
         title = entity.title,
         description = entity.description,
         imageUrl = entity.imageUrl,
         isFavorite = false,
         price = entity.price
      )
      return if (productList.add(newProduct)) {
         ApiProductResponse(
            message = "Successfully created!",
            productEntity = newProduct
         )
      } else {
         ApiProductResponse(
            "Something went wrong!",
            productEntity = null
         )
      }
   }

   override suspend fun updateProduct(productEntity: ProductEntity): ApiProductResponse {
      val product = productList.find { it.id == productEntity.id }
      return if (product != null) {
         productList.remove(product)
         val newProduct = productEntity.copy(
            id = product.id,
            title = productEntity.title,
            description = productEntity.description,
            imageUrl = productEntity.imageUrl,
            price = productEntity.price,
            isFavorite = productEntity.isFavorite
         )
         productList.add(newProduct)
         ApiProductResponse(
            message = "Product Successfully updated.",
            productEntity = newProduct,
         )
      } else {
         ApiProductResponse(
            message = "No such Product!",
            productEntity = null,
         )
      }
   }

   override suspend fun getProductById(productId: String?): ApiProductResponse {
      val product = productList.find { it.id == productId }
      return if (product != null) {
         ApiProductResponse(
            message = "Product found!",
            productEntity = product
         )
      } else {
         ApiProductResponse(
            message = "No such Product!",
            productEntity = null,
         )
      }
   }

   override suspend fun getAllProducts(): ApiProductListResponse {
      return ApiProductListResponse(
         message = "All Products.",
         products = productList
      )
   }

   override suspend fun deleteProduct(productId: String?): ApiProductResponse {
      val product = productList.find { it.id == productId }
      return if (product != null) {
         productList.remove(product)
         ApiProductResponse(
            message = "Product Successfully deleted.",
            productEntity = null,
         )
      } else {
         ApiProductResponse(
            message = "No such Product!",
            productEntity = null,
         )
      }
   }

   //suspend fun getProductById(id: String): Product? {
   //   return products.findOneById(id)
   //}
   //
   //suspend fun createProduct(product: Product): String {
   //   val newProduct = Product(
   //      id = ObjectId().toString()
   //      // some other fields...
   //   )
   //   products.insertOne(newProduct).wasAcknowledged()
   //   return newProduct.id
   //}
   //
   //suspend fun updateProduct(product: Product): String {
   //   val productExists = products.findOneById(product.id) != null
   //   return if (productExists) {
   //      products.updateOneById(product.id, product).wasAcknowledged()
   //      product.id
   //   } else {
   //      throw IllegalArgumentException()
   //   }
   //}
   //
   //suspend fun deleteProduct(productId: String): Boolean {
   //   val product = products.findOne(Product::id eq productId)
   //   return product?.let {
   //      products.deleteOneById(it.id).wasAcknowledged()
   //   } ?: false
   //}

}