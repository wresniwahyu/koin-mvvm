package com.www.app.ui.detail

import com.www.app.data.model.movie.MovieResponse
import com.www.app.ui.base.BaseNavigator

interface DetailNavigator : BaseNavigator {

    fun showSuccessDetail(response: MovieResponse)

}