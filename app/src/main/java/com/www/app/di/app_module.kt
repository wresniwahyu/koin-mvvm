package com.www.app.di

import com.www.app.ui.detail.DetailViewModel
import com.www.app.ui.main.MainActivityViewModel
import com.www.app.ui.main.MainAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { MainAdapter(androidContext()) }

    viewModel { MainActivityViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}