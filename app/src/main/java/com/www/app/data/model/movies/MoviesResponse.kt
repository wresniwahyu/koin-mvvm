package com.www.app.data.model.movies

data class MoviesResponse(
    val page: Int,
    val total_pages: Int,
    val results: ArrayList<ResultsItem>,
    val total_results: Int
)
