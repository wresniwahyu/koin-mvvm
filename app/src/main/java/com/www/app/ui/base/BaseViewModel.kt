package com.www.app.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>: ViewModel() {

    val mIsLoading = ObservableBoolean(false)

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var mNavigator: WeakReference<N>? = null

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.mIsLoading.set(isLoading)
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun getNavigator(): N? {
        return mNavigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }


}