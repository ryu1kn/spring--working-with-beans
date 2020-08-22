package com.example.bookstore

import io.undertow.Undertow
import org.springframework.context.support.GenericApplicationContext
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.WebHandler
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.WEB_HANDLER_BEAN_NAME
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext
import java.util.function.Supplier

fun main() {
    val bookCatalog = BookCatalogService(RealBookRepo(), RealAuthorRepo())
    val router = Router(bookCatalog)

    val context = GenericApplicationContext().apply {
        registerBean(WEB_HANDLER_BEAN_NAME, WebHandler::class.java, Supplier {
            RouterFunctions.toWebHandler(router.route)
        })
        refresh()
    }

    undertowServer {
        val handler = applicationContext(context).build()
        setHandler(UndertowHttpHandlerAdapter(handler))
        addHttpListener(8080, "0.0.0.0")
    }.start()
}

private fun undertowServer(build: Undertow.Builder.() -> Unit) =
    Undertow.builder().apply(build).build()
