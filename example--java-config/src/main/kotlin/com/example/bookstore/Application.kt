package com.example.bookstore

import io.undertow.Undertow
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.WEB_HANDLER_BEAN_NAME
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext

@Configuration
class AppConfig {
    @Bean(WEB_HANDLER_BEAN_NAME)
    fun bookstoreWebHandler(router: Router) = RouterFunctions.toWebHandler(router.route)

    @Bean
    fun router(bookCatalogService: BookCatalogService) = Router(bookCatalogService)

    @Bean
    fun bookCatalogService(bookRepo: BookRepo, authorRepo: AuthorRepo) =
        BookCatalogService(bookRepo, authorRepo)

    @Bean
    fun bookRepo() = RealBookRepo()

    @Bean
    fun authorRepo() = RealAuthorRepo()
}

fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    val handler = applicationContext(context).build()

    undertowServer {
        setHandler(UndertowHttpHandlerAdapter(handler))
        addHttpListener(8080, "0.0.0.0")
    }.start()
}

private fun undertowServer(build: Undertow.Builder.() -> Unit) =
    Undertow.builder().apply(build).build()
