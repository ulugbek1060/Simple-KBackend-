package com.example.di

import com.example.data.repository.ProductsRepository
import com.example.data.repository.ProductsRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
   single<ProductsRepository> {
      ProductsRepositoryImpl()
   }
}