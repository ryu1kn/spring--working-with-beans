package com.example.bookstore

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookCatalogService @Autowired constructor(private val bookRepo: BookRepo, private val authorRepo: AuthorRepo) {
    fun listBooks(): List<Book> = bookRepo.list().mapNotNull {
        raw -> authorRepo.resolveName(raw.authorId)?.let { Book(raw, it) }
    }
}

data class Book(val name: String, val author: String) {
    constructor(rb: RawBook, author: String) : this(rb.name, author)
}
