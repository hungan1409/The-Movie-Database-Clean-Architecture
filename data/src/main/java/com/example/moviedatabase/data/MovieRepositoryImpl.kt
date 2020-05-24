package com.example.moviedatabase.data

import com.example.moviedatabase.data.model.GenreEntityMapper
import com.example.moviedatabase.data.model.MovieEntityMapper
import com.example.moviedatabase.data.remote.api.MovieApi
import com.example.moviedatabase.domain.model.Genre
import com.example.moviedatabase.domain.model.Movie
import com.example.moviedatabase.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieEntityMapper: MovieEntityMapper,
    private val genreEntityMapper: GenreEntityMapper
) : MovieRepository {
    override fun getMovieListPopular(page: Int): Single<List<Movie>> {
        return movieApi.getMovieListPopular(page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }

    override fun getMovieListTopRated(page: Int): Single<List<Movie>> {
        return movieApi.getMovieListTopRated(page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }

    override fun getMovieListUpcoming(page: Int): Single<List<Movie>> {
        return movieApi.getMovieListUpcoming(page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }

    override fun getMovieGenres(): Single<List<Genre>> {
        return movieApi.getMovieGenres().map {
            it.results.map { genre ->
                genreEntityMapper.mapToDomain(genre)
            }
        }
    }
}
