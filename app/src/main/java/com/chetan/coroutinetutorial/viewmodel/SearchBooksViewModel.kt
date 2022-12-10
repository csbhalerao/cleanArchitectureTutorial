package com.chetan.coroutinetutorial.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.chetan.coroutinetutorial.data.apiservice.helper.Resource
import com.chetan.coroutinetutorial.data.apiservice.helper.ResourceError
import com.chetan.coroutinetutorial.data.mapper.SearchResponseToBooks
import com.chetan.coroutinetutorial.data.model.SearchResponse
import com.chetan.coroutinetutorial.data.repo.SearchBooksRepo
import com.chetan.coroutinetutorial.usecase.model.Book
import javax.inject.Inject

class SearchBooksViewModel @Inject constructor(
    private val searchBookRepo: SearchBooksRepo,
    private val mapper: SearchResponseToBooks
) : ViewModel() {
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<ResourceError> = MutableLiveData()
    val booksLiveData = MutableLiveData<MutableList<Book>>(mutableListOf())
    val callObserver: Observer<Resource<SearchResponse>> =
        Observer { t -> processResponse(t) }

    fun searchBook(query: String) {
        searchBookRepo.call(query).observeForever {
            callObserver.onChanged(it)
        }
    }

    private fun processResponse(response: Resource<SearchResponse>?) {
        response?.let {
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading()
                }
                Resource.Status.SUCCESS -> {
                    setSuccess(response.data)
                }
                Resource.Status.ERROR -> {
                    setError(response.resourceError)
                }
            }
        } ?: showCustomError()
    }

    private fun showCustomError() {
    }

    private fun setError(resourceError: ResourceError?) {
        isLoadingLiveData.value = false
        isErrorLiveData.value = true
        isSuccessLiveData.value = false
    }

    private fun setSuccess(data: SearchResponse?) {
        data?.let {
            booksLiveData.value?.clear()
            val books = mapper.map(searchResponse = it)

            booksLiveData.value?.addAll(books)
        }
        isLoadingLiveData.value = false
        isErrorLiveData.value = false
        isSuccessLiveData.value = true
    }

    private fun setLoading() {
        isLoadingLiveData.value = true
        isErrorLiveData.value = false
        isSuccessLiveData.value = false
    }

    fun isLoading(): LiveData<Boolean> = isLoadingLiveData
    fun isSuccessful(): LiveData<Boolean> = isSuccessLiveData
}
