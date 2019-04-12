package com.www.app.ui.main

import com.www.app.data.model.movies.MoviesResponse
import com.www.app.ui.base.BaseNavigator

interface MainActivityNavigator : BaseNavigator {

    fun showSuccessGetMovies(response: MoviesResponse)

}