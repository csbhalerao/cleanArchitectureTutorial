package com.chetan.coroutinetutorial.data.apiservice

import com.chetan.coroutinetutorial.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBooksService {
    @GET("/books/v1/volumes")
    fun search(@Query("q") query: String): Call<SearchResponse>
}
