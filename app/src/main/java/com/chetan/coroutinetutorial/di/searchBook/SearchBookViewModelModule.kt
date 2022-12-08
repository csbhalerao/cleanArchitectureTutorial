package com.chetan.coroutinetutorial.di.searchBook

import com.chetan.coroutinetutorial.data.apiservice.SearchBooksService
import com.chetan.coroutinetutorial.data.mapper.SearchResponseToBooks
import com.chetan.coroutinetutorial.data.repo.SearchBooksRepo
import com.chetan.coroutinetutorial.data.repo.SearchBooksRepoImpl
import com.chetan.coroutinetutorial.viewmodel.SearchBooksViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class SearchBookViewModelModule {
    @Provides
    fun providesSearchBookRepo(service: SearchBooksService): SearchBooksRepo {
        return SearchBooksRepoImpl(service)
    }

    @Provides
    fun providesBookMapper(): SearchResponseToBooks {
        return SearchResponseToBooks()
    }

    @Provides
    fun providesSearchBookViewModel(
        repo: SearchBooksRepo,
        mapper: SearchResponseToBooks
    ): SearchBooksViewModel {
        return SearchBooksViewModel(repo, mapper)
    }
}
