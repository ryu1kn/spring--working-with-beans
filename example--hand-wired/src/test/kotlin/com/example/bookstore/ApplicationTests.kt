package com.example.bookstore

import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

typealias BookInfo = Map<String, String>

class ApplicationTests {
    private val bookRepo = BookRepo { listOf(RawBook("Book Z", "author-z")) }
    private val authorRepo = AuthorRepo { id -> if (id == "author-z") "Author Z" else null }
    private val bookCatalog = BookCatalogService(bookRepo, authorRepo)
    private val router = Router(bookCatalog)

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
