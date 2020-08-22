package com.example.bookstore

import io.undertow.Undertow
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext

fun main() {
    val context = ClassPathXmlApplicationContext("beans.xml")

    undertowServer {
        val handler = applicationContext(context).build()
        setHandler(UndertowHttpHandlerAdapter(handler))
        addHttpListener(8080, "0.0.0.0")
    }.start()
}

private fun undertowServer(build: Undertow.Builder.() -> Unit) =
    Undertow.builder().apply(build).build()
