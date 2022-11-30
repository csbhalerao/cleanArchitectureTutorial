package com.example.coroutinetutorial.data.apiservice

import com.example.coroutinetutorial.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/books/v1/volumes")
    suspend fun searchBooks(): Response<SearchResponse>
}
