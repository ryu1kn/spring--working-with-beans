package com.example.bookstore

import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebHandler
import reactor.core.publisher.Mono

class BookstoreWebHandler(private val router: Router) : WebHandler {
    override fun handle(exchange: ServerWebExchange): Mono<Void> =
        RouterFunctions.toWebHandler(router.route).handle(exchange)
}
