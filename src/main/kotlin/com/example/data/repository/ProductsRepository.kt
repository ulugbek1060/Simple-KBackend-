package com.example.data.repository

import com.example.data.model.ApiProductListResponse
import com.example.data.model.ApiProductRequestEntity
import com.example.data.model.ApiProductResponse
import com.example.data.model.ProductEntity

interface ProductsRepository {

   val productList: List<ProductEntity>

   suspend fun createProduct(entity: ApiProductRequestEntity): ApiProductResponse

   suspend fun updateProduct(productEntity: ProductEntity): ApiProductResponse

   suspend fun getProductById(productId: String?): ApiProductResponse

   suspend fun getAllProducts(): ApiProductListResponse

   suspend fun deleteProduct(productId: String?): ApiProductResponse

}