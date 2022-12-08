package com.chetan.coroutinetutorial.di.searchBook

import com.chetan.coroutinetutorial.data.apiservice.SearchBooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchBookServiceModule {
    @Singleton
    @Provides
    fun providesSearchBookService(retrofit: Retrofit): SearchBooksService {
        return retrofit.create(SearchBooksService::class.java)
    }
}
