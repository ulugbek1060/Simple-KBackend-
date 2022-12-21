package com.example.data.database

import com.example.data.model.ProductEntity
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class CompanyDatabase {

   private val client = KMongo.createClient().coroutine
   private val database = client.getDatabase("CompanyDatabase")
   private val products = database.getCollection<ProductEntity>()

}