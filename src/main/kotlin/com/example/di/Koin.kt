package com.example.di

import com.example.data.repository.cart_repo.CartRepository
import com.example.data.repository.cart_repo.CartRepositoryImpl
import com.example.data.repository.order_repo.OrderRepository
import com.example.data.repository.order_repo.OrderRepositoryImpl
import com.example.data.repository.product_repo.ProductsRepository
import com.example.data.repository.product_repo.ProductsRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
   single<ProductsRepository> {
      ProductsRepositoryImpl()
   }

   single<OrderRepository> {
      OrderRepositoryImpl()
   }

   single<CartRepository> {
      CartRepositoryImpl()
   }
}