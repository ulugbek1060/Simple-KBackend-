package com.example.data.repository.product_repo

import com.example.data.model.product_entity.ApiProductListResponse
import com.example.data.model.product_entity.ApiProductRequestEntity
import com.example.data.model.product_entity.ApiProductResponse
import com.example.data.model.product_entity.ProductEntity

interface ProductsRepository {

   val productList: List<ProductEntity>

   suspend fun createProduct(entity: ApiProductRequestEntity): ApiProductResponse

   suspend fun updateProduct(productEntity: ProductEntity): ApiProductResponse

   suspend fun getProductById(productId: String?): ApiProductResponse

   suspend fun getAllProducts(): ApiProductListResponse

   suspend fun deleteProduct(productId: String?): ApiProductResponse

}