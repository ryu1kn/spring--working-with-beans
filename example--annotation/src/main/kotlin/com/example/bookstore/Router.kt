package com.example.bookstore

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class Router @Autowired constructor(private val bookCatalog: BookCatalogService) {
    val route: RouterFunction<ServerResponse> = RouterFunctions.route(
        GET("/books"), HandlerFunction<ServerResponse> {
            ServerResponse.ok().body(BodyInserters.fromObject(
                mapOf("books" to bookCatalog.listBooks())
            ))
        }
    )
}
