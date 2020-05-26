package com.example.moviedatabase.domain.repository

import com.example.moviedatabase.domain.model.*
import io.reactivex.Single

interface MovieRepository {
    fun getMovieListPopular(page: Int): Single<List<Movie>>

    fun getMovieListTopRated(page: Int): Single<List<Movie>>

    fun getMovieListUpcoming(page: Int): Single<List<Movie>>

    fun getMovieGenres(): Single<List<Genre>>

    fun getMovieDetail(movieId: Int): Single<MovieDetail>

    fun getMovieVideos(movieId: Int): Single<MovieVideos>

    fun getCredits(movieId: Int): Single<MovieCredits>

    fun getMovieRecommendations(movieId: Int): Single<List<Movie>>
}
