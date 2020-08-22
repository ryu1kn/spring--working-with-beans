package com.example.bookstore

import org.springframework.stereotype.Component

fun interface BookRepo {
    fun list(): List<RawBook>
}

@Component
class RealBookRepo : BookRepo {
    private val booksLoadedFromDB = listOf(
        RawBook("Book A", "author-a"),
        RawBook("Book B", "author-b")
    )

    override fun list(): List<RawBook> = booksLoadedFromDB
}

data class RawBook(val name: String, val authorId: String)
