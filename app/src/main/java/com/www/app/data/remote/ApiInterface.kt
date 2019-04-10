package com.www.app.data.remote

import com.www.app.data.model.movie.MovieResponse
import com.www.app.data.model.movies.MoviesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String,
                         @Query("page") page: Int)
            : Observable<Response<MoviesResponse>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") api_key: String,
                          @Query("page") page: Int)
            : Observable<Response<MoviesResponse>>

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movie_id: Int,
                 @Query("api_key") api_key: String)
            : Observable<Response<MovieResponse>>

}