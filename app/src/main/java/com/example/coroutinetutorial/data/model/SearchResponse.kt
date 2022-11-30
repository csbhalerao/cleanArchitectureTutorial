package com.example.coroutinetutorial.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("publishedDate")
    val publishedDate: String
)
