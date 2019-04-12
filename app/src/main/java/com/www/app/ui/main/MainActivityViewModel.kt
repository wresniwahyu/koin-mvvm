package com.www.app.ui.main

import com.www.app.BuildConfig
import com.www.app.data.model.movies.MoviesResponse
import com.www.app.data.remote.ApiInterface
import com.www.app.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivityViewModel(private val apiInterface: ApiInterface) : BaseViewModel<MainActivityNavigator>() {

    fun getMovies(page: Int) {
        getNavigator()?.let {
            val response = object : DisposableObserver<Response<MoviesResponse>>() {

                override fun onStart() {
                    it.showHideLoading(true)
                }

                override fun onComplete() {
                    it.showHideLoading(false)
                }

                override fun onNext(t: Response<MoviesResponse>) {
                    if (t.isSuccessful) {
                        it.showSuccessGetMovies(t.body())
                    }
                }

                override fun onError(e: Throwable) {
                    e.message?.let { error -> it.showError(error) }
                }
            }

            compositeDisposable.add(response)
            apiInterface.getPopularMovies(BuildConfig.API_KEY_MDB, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response)

        }
    }

}