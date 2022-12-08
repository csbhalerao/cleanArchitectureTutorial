package com.chetan.coroutinetutorial.data.mapper

import com.chetan.coroutinetutorial.data.model.SearchResponse
import com.chetan.coroutinetutorial.usecase.model.Book

class SearchResponseToBooks() {
    fun map(searchResponse: SearchResponse): MutableList<Book> {
        val books: MutableList<Book> = mutableListOf()
        searchResponse.items.forEach { bookItem ->
            val book = Book(
                name = bookItem.volumeInfo.title?.let { it } ?: "",
                authors = bookItem.volumeInfo.authors,
                description = bookItem.volumeInfo.description?.let { it } ?: "",
                image = bookItem.volumeInfo.imageLinks.thumbnail?.let { it } ?: "",
                publishDate = bookItem.volumeInfo.publishedDate?.let { it } ?: ""
            )
            books.add(book)
        }
        return books
    }
}
