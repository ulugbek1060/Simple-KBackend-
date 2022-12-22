package com.example.data.repository.product_repo

import com.example.data.model.product_entity.ApiProductListResponse
import com.example.data.model.product_entity.ApiProductRequestEntity
import com.example.data.model.product_entity.ApiProductResponse
import com.example.data.model.product_entity.ProductEntity
import org.bson.types.ObjectId

class ProductsRepositoryImpl : ProductsRepository {

   override val productList: ArrayList<ProductEntity> = arrayListOf(
      ProductEntity(
         id = "p1",
         title = "Red Shirt",
         description = "A red shirt - it is pretty red!",
         price = 29.99,
         imageUrl = "https://cdn.pixabay.com/photo/2016/10/02/22/17/red-t-shirt-1710578_1280.jpg",
         isFavorite = false
      ),
      ProductEntity(
         id = "p2",
         title = "Trousers",
         description = "A nice pair of trousers.",
         price = 59.99,
         imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Trousers%2C_dress_%28AM_1960.022-8%29.jpg/512px-Trousers%2C_dress_%28AM_1960.022-8%29.jpg",
         isFavorite = false
      ),
      ProductEntity(
         id = "p3",
         title = "Yellow Scarf",
         description = "Warm and cozy - exactly what you need for the winter.",
         price = 59.99,
         imageUrl = "https://live.staticflickr.com/4043/4438260868_cc79b3369d_z.jpg",
         isFavorite = false
      )
   )

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
      val existingProductIndex = productList.indexOf(productEntity)
      return if (existingProductIndex != -1) {
         val newProduct = productEntity.copy(
            id = productEntity.id,
            title = productEntity.title,
            description = productEntity.description,
            imageUrl = productEntity.imageUrl,
            price = productEntity.price,
            isFavorite = productEntity.isFavorite
         )
         productList.add(existingProductIndex, newProduct)
         ApiProductResponse(
            message = "Product Successfully updated.",
            productEntity = newProduct,
         )
      } else {
         ApiProductResponse(
            message = "No such Product!"
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
            message = "No such Product!"
         )
      }
   }

   override suspend fun getAllProducts(): ApiProductListResponse {
      return ApiProductListResponse(
         message = "All Products.",
         products = productList.sortedBy { it.title }
      )
   }

   override suspend fun deleteProduct(productId: String?): ApiProductResponse {
      val product = productList.find { it.id == productId }
      return if (product != null) {
         productList.remove(product)
         ApiProductResponse(
            message = "Product Successfully deleted."
         )
      } else {
         ApiProductResponse(
            message = "No such Product!"
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