package com.example.moviedatabase.data.remote.api

import com.example.moviedatabase.data.remote.response.GetGenresResponse
import com.example.moviedatabase.data.remote.response.GetMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getMovieListPopular(@Query(ApiParams.PAGE) page: Int): Single<GetMovieResponse>

    @GET("movie/top_rated")
    fun getMovieListTopRated(@Query(ApiParams.PAGE) page: Int): Single<GetMovieResponse>

    @GET("movie/upcoming")
    fun getMovieListUpcoming(@Query(ApiParams.PAGE) page: Int): Single<GetMovieResponse>

    @GET("genre/movie/list")
    fun getMovieGenres(): Single<GetGenresResponse>

}

object ApiParams {
    const val PAGE = "page"
}
