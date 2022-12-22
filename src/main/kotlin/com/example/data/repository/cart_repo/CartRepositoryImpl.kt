package com.example.data.repository.cart_repo

import com.example.data.model.cart_entity.ApiCartListResponse
import com.example.data.model.cart_entity.ApiCartRequestEntity
import com.example.data.model.cart_entity.ApiCartResponse
import com.example.data.model.cart_entity.CartEntity
import org.bson.types.ObjectId

class CartRepositoryImpl : CartRepository {

   override val cartList: ArrayList<CartEntity> = arrayListOf()

   override suspend fun createCart(entity: ApiCartRequestEntity): ApiCartResponse {
      val newEntity = CartEntity(
         id = ObjectId().toString(), title = entity.title, quantity = entity.quantity, price = entity.price
      )
      cartList.add(newEntity)
      return ApiCartResponse(
         message = "Cart Successfully created!", cart = newEntity
      )
   }

   override suspend fun updateCart(entity: CartEntity): ApiCartResponse {
      val existingCartIndex = cartList.indexOf(entity)
      return if (existingCartIndex != -1) {
         val newCart = CartEntity(
            id = entity.id,
            title = entity.title,
            quantity = entity.quantity,
            price = entity.price
         )
         cartList.add(existingCartIndex, newCart)
         ApiCartResponse(
            message = "Cart successfully updated!", cart = newCart
         )
      } else {
         ApiCartResponse(
            message = "Update failed. Not such a cart!"
         )
      }
   }

   override suspend fun deleteCart(cartId: String): String {
      val existingCart = cartList.find { it.id == cartId }
      return if (existingCart != null) {
         cartList.remove(existingCart)
         "Cart removed!"
      } else {
         "Not found cart!"
      }
   }

   override suspend fun getCartById(cartId: String): ApiCartResponse {
      val existingCart = cartList.find { it.id == cartId }
      return if (existingCart != null) {
         ApiCartResponse(
            message = "Cart: ${existingCart.id}", cart = existingCart
         )
      } else {
         ApiCartResponse(
            message = "Not such a cart!"
         )
      }
   }

   override suspend fun getCartList(): ApiCartListResponse {
      return ApiCartListResponse(
         message = "All carts", cartOfList = cartList
      )
   }
}