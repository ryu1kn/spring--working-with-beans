package com.example.bookstore

import io.undertow.Undertow
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler

fun main() {
    val bookCatalog = BookCatalogService(RealBookRepo(), RealAuthorRepo())
    val handler = toHttpHandler(Router(bookCatalog).route)
    undertowServer {
        setHandler(UndertowHttpHandlerAdapter(handler))
        addHttpListener(8080, "0.0.0.0")
    }.start()
}

private fun undertowServer(build: Undertow.Builder.() -> Unit) =
    Undertow.builder().apply(build).build()
