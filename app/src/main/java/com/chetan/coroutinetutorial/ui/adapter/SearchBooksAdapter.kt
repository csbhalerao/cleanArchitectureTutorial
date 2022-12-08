package com.chetan.coroutinetutorial.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chetan.coroutinetutorial.R
import com.chetan.coroutinetutorial.databinding.ItemBookBinding
import com.chetan.coroutinetutorial.usecase.model.Book

class SearchBooksAdapter(private val books: MutableList<Book>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @LayoutRes
        val LAYOUT_ITEM_BOOK = R.layout.item_book
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBookBinding>(
            layoutInflater,
            LAYOUT_ITEM_BOOK,
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            val book = books[position]
            holder.binding.book = book
        }
    }

    override fun getItemCount(): Int {
        return books.count()
    }

    class BookViewHolder : RecyclerView.ViewHolder {
        lateinit var binding: ItemBookBinding

        constructor(view: View) : super(view)

        constructor(binding: ItemBookBinding) : this(binding.root) {
            this.binding = binding
        }
    }
}
