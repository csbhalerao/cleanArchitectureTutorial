package com.chetan.coroutinetutorial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chetan.coroutinetutorial.R
import com.chetan.coroutinetutorial.databinding.ActivityMainBinding
import com.chetan.coroutinetutorial.ui.adapter.SearchBooksAdapter
import com.chetan.coroutinetutorial.usecase.model.Book
import com.chetan.coroutinetutorial.viewmodel.SearchBooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var searchViewModel: SearchBooksViewModel
    lateinit var binding: ActivityMainBinding
    val booksAdapter: SearchBooksAdapter by lazy {
        SearchBooksAdapter(searchViewModel.booksLiveData.value as MutableList<Book>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle("Search books")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.searchViewModel = searchViewModel
        setRecyclerView()
        observeResponse()
        searchViewModel.searchBook("kotlin")
    }

    private fun observeResponse() {
        searchViewModel.booksLiveData.observe(this, {
            println("in obs:" + it)
            it?.let {
                println("in let:" + it)
                booksAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewBook.layoutManager = linearLayoutManager
        binding.recyclerViewBook.adapter = booksAdapter
    }
}
