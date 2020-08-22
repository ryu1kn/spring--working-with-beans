package com.example.bookstore

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebHandler
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.core.publisher.Mono

@Component(WebHttpHandlerBuilder.WEB_HANDLER_BEAN_NAME)
class BookstoreWebHandler(private val router: Router) : WebHandler {
    override fun handle(exchange: ServerWebExchange): Mono<Void> =
        RouterFunctions.toWebHandler(router.route).handle(exchange)
}

