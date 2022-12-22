package com.example.plugins

import com.example.routes.cartsRoute
import com.example.routes.ordersRoute
import com.example.routes.productsRoute
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
   routing {
      productsRoute()
      ordersRoute()
      cartsRoute()
   }
}
