package com.www.app.ui.main

import com.www.app.data.model.movies.ResultsItem
import com.www.app.ui.base.BaseNavigator

interface MainActivityNavigator: BaseNavigator {

    fun showSuccessGetMovies(response: ArrayList<ResultsItem>)

}