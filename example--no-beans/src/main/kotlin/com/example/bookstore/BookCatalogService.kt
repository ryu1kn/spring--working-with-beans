package com.example.bookstore

class BookCatalogService(private val bookRepo: BookRepo, private val authorRepo: AuthorRepo) {
    fun listBooks(): List<Book> = bookRepo.list().mapNotNull {
        raw -> authorRepo.resolveName(raw.authorId)?.let { Book(raw, it) }
    }
}

data class Book(val name: String, val author: String) {
    constructor(rb: RawBook, author: String) : this(rb.name, author)
}
