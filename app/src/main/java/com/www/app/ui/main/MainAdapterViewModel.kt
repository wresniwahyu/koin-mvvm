package com.www.app.ui.main

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.www.app.BuildConfig
import com.www.app.data.model.movies.ResultsItem

class MainAdapterViewModel(val item: ResultsItem) {

    fun getImageUrl(): String = BuildConfig.BASE_IMAGE_URL + item.poster_path

    object DataBindingAdapter {
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }

}