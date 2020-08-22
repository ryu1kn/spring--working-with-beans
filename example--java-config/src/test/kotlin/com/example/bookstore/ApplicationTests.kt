package com.example.bookstore

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

typealias BookInfo = Map<String, String>

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [AppConfig::class, ApplicationTests.TestConfig::class])
class ApplicationTests {
    @TestConfiguration
    class TestConfig {
        @Bean
        fun bookRepo() = BookRepo { listOf(RawBook("Book Z", "author-z")) }

        @Bean
        fun authorRepo() = AuthorRepo { id -> if (id == "author-z") "Author Z" else null }
    }

    @Autowired
    lateinit var router: Router

    @Test
    fun `Retrieve all books`() {
        val testClient = WebTestClient.bindToRouterFunction(router.route).build()
        testClient.get().uri("/books")
            .exchange()
            .expectStatus().isOk()
            .expectBody<Map<String,List<BookInfo>>>()
            .isEqualTo(mapOf("books" to listOf(
                mapOf("name" to "Book Z", "author" to "Author Z")
            )))
    }
}
