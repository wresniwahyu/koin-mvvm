package com.www.app.ui.detail

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.www.app.BuildConfig
import com.www.app.data.model.movie.MovieResponse
import com.www.app.data.remote.ApiInterface
import com.www.app.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DetailViewModel(val apiInterface: ApiInterface) : BaseViewModel<DetailNavigator>() {

    var response: MovieResponse? = null

    fun getMovieDetail(id: Int) {
        getNavigator()?.let {
            val response = object : DisposableObserver<Response<MovieResponse>>() {

                override fun onStart() {
                    it.showHideLoading(true)
                }

                override fun onComplete() {
                    it.showHideLoading(false)
                }

                override fun onNext(t: Response<MovieResponse>) {
                    if (t.isSuccessful) {
                        response = t.body()
                        it.showSuccessDetail(t.body())
                    }
                }

                override fun onError(e: Throwable) {
                    e.message?.let { error -> it.showError(error) }
                }
            }

            compositeDisposable.add(response)
            apiInterface.getMovie(id, BuildConfig.API_KEY_MDB)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response)
        }
    }

    fun getImageUrl(): String = BuildConfig.BASE_IMAGE_URL + response?.poster_path

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