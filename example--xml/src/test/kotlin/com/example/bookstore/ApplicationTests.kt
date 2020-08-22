package com.example.bookstore

import org.junit.jupiter.api.Test
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

typealias BookInfo = Map<String, String>

class ApplicationTests {
    class MockBookRepo : BookRepo {
        override fun list() = listOf(RawBook("Book Z", "author-z"))
    }

    class MockAuthorRepo : AuthorRepo {
        override fun resolveName(id: String) = if (id == "author-z") "Author Z" else null
    }

    private val router = ClassPathXmlApplicationContext("beans.xml", "beans-test.xml").getBean(Router::class.java)

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
