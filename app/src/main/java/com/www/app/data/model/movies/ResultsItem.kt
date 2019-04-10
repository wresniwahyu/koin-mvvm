package com.www.app.data.model.movies

data class ResultsItem(
	val overview: String,
	val original_language: String,
	val original_title: String,
	val video: Boolean,
	val title: String,
	val genre_ids: List<Int?>,
	val poster_path: String,
	val backdrop_path: String,
	val release_date: String,
	val vote_average: Double,
	val popularity: Double,
	val id: Int,
	val adult: Boolean,
	val vote_count: Int
)
