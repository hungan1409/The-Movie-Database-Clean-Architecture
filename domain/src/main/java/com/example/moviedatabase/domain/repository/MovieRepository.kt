package com.example.moviedatabase.domain.repository

import com.example.moviedatabase.domain.model.Genre
import com.example.moviedatabase.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getMovieListPopular(page: Int): Single<List<Movie>>

    fun getMovieListTopRated(page: Int): Single<List<Movie>>

    fun getMovieListUpcoming(page: Int): Single<List<Movie>>

    fun getMovieGenres(): Single<List<Genre>>

}
