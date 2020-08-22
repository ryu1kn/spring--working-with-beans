package com.example.bookstore

fun interface AuthorRepo {
    fun resolveName(id: String): String?
}

class RealAuthorRepo : AuthorRepo {
    private val authorNamesFromTheNetwork = mapOf(
        "author-a" to "Author A",
        "author-b" to "Author B"
    )

    override fun resolveName(id: String) = authorNamesFromTheNetwork[id]
}
