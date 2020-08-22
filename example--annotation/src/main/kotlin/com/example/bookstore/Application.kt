package com.example.bookstore

import io.undertow.Undertow
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext

@Configuration
@ComponentScan(basePackages = ["com.example.bookstore"])
class Application

fun main() {
    val context = AnnotationConfigApplicationContext(Application::class.java)
    val handler = applicationContext(context).build()

    undertowServer {
        setHandler(UndertowHttpHandlerAdapter(handler))
        addHttpListener(8080, "0.0.0.0")
    }.start()
}

private fun undertowServer(build: Undertow.Builder.() -> Unit) =
    Undertow.builder().apply(build).build()
