package com.chetan.coroutinetutorial.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("source")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
    /*view.load(url){
        println("in:"+url)
        placeholder(R.drawable.ic_launcher_foreground)
        crossfade(true).crossfade(500)
        transformations(RoundedCornersTransformation(16f))
    }*/
}
