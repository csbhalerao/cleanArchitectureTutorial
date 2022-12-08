package com.chetan.coroutinetutorial.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val items: List<BookItem>
)

data class BookItem(
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @SerializedName("title")
    val title: String?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks,
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("description")
    val description: String?
)

data class ImageLinks(
    @SerializedName("thumbnail")
    val thumbnail: String?
)
