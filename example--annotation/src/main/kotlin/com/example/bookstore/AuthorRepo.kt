package com.example.bookstore

import org.springframework.stereotype.Component

fun interface AuthorRepo {
    fun resolveName(id: String): String?
}

@Component
class RealAuthorRepo : AuthorRepo {
    private val authorNamesFromTheNetwork = mapOf(
        "author-a" to "Author A",
        "author-b" to "Author B"
    )

    override fun resolveName(id: String) = authorNamesFromTheNetwork[id]
}
