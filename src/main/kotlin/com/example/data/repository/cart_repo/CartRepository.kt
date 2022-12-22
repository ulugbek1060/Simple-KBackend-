package com.example.data.repository.cart_repo

import com.example.data.model.cart_entity.ApiCartListResponse
import com.example.data.model.cart_entity.ApiCartRequestEntity
import com.example.data.model.cart_entity.ApiCartResponse
import com.example.data.model.cart_entity.CartEntity
import com.example.data.model.order_entity.ApiOrderListResponse
import com.example.data.model.order_entity.ApiOrderRequestEntity
import com.example.data.model.order_entity.ApiOrderResponse
import com.example.data.model.order_entity.OrderEntity

interface CartRepository {

   val cartList: ArrayList<CartEntity>

   suspend fun createCart(entity: ApiCartRequestEntity): ApiCartResponse

   suspend fun updateCart(entity: CartEntity): ApiCartResponse

   suspend fun deleteCart(cartId: String): String

   suspend fun getCartById(cartId: String): ApiCartResponse

   suspend fun getCartList(): ApiCartListResponse
}