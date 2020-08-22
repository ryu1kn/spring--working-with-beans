package com.example.bookstore

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

typealias BookInfo = Map<String, String>

@ExtendWith(SpringExtension::class)
@ContextConfiguration
class ApplicationTests {
    @TestConfiguration
    @ComponentScan("com.example.bookstore")
    class TestApp

    @TestComponent @Primary
    class MockBookRepo : BookRepo {
        override fun list() = listOf(RawBook("Book Z", "author-z"))
    }

    @TestComponent @Primary
    class MockAuthorRepo : AuthorRepo {
        override fun resolveName(id: String) = if (id == "author-z") "Author Z" else null
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
