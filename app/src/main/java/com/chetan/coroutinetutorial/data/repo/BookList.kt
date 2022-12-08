package com.chetan.coroutinetutorial.data.repo

import androidx.lifecycle.LiveData
import com.chetan.coroutinetutorial.data.apiservice.SearchBooksService
import com.chetan.coroutinetutorial.data.apiservice.helper.NetworkCall
import com.chetan.coroutinetutorial.data.apiservice.helper.Resource
import com.chetan.coroutinetutorial.data.model.SearchResponse

interface SearchBooksRepo {
    fun call(query: String): LiveData<Resource<SearchResponse>>
}

class SearchBooksRepoImpl(private val service: SearchBooksService) : SearchBooksRepo {
    private val searchCall = NetworkCall<SearchResponse>()

    override fun call(query: String): LiveData<Resource<SearchResponse>> {
        val call = service.search(query)
        return searchCall.exec(call)
    }
}
