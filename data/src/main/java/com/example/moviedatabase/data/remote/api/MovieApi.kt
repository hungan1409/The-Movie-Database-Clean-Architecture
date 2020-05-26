package com.example.moviedatabase.data.remote.api

import com.example.moviedatabase.data.model.MovieCreditsEntity
import com.example.moviedatabase.data.model.MovieDetailEntity
import com.example.moviedatabase.data.model.MovieVideosEntity
import com.example.moviedatabase.data.remote.response.GetGenresResponse
import com.example.moviedatabase.data.remote.response.GetMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path(ApiParams.MOVIE_ID) movieId: Int): Single<MovieDetailEntity>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path(ApiParams.MOVIE_ID) movieId: Int): Single<MovieVideosEntity>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path(ApiParams.MOVIE_ID) movieId: Int): Single<MovieCreditsEntity>
}

object ApiParams {
    const val PAGE = "page"
    const val MOVIE_ID = "movie_id"
}
