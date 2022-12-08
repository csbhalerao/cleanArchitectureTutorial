package com.chetan.coroutinetutorial.usecase.model

data class Book(
    val name: String = "",
    val authors: List<String> = listOf(""),
    val description: String = "",
    val image: String = "",
    val publishDate: String = ""
)
