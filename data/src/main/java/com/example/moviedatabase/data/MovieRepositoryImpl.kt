package com.example.moviedatabase.data

import com.example.moviedatabase.data.model.*
import com.example.moviedatabase.data.remote.api.MovieApi
import com.example.moviedatabase.domain.model.*
import com.example.moviedatabase.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieEntityMapper: MovieEntityMapper,
    private val genreEntityMapper: GenreEntityMapper,
    private val movieDetailEntityMapper: MovieDetailEntityMapper,
    private val movieVideosEntityMapper: MovieVideosEntityMapper,
    private val movieCreditsEntityMapper: MovieCreditsEntityMapper,
    private val movieCommentEntityMapper: MovieCommentEntityMapper
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
            it.genres?.map { genre ->
                genreEntityMapper.mapToDomain(genre)
            }
        }
    }

    override fun getMovieDetail(movieId: Int): Single<MovieDetail> {
        return movieApi.getMovieDetail(movieId).map {
            movieDetailEntityMapper.mapToDomain(it)
        }
    }

    override fun getMovieVideos(movieId: Int): Single<MovieVideos> {
        return movieApi.getMovieVideos(movieId).map {
            movieVideosEntityMapper.mapToDomain(it)
        }
    }

    override fun getMovieCredits(movieId: Int): Single<MovieCredits> {
        return movieApi.getMovieCredits(movieId).map {
            movieCreditsEntityMapper.mapToDomain(it)
        }
    }

    override fun getMovieComments(movieId: Int): Single<List<MovieComment>> {
        return movieApi.getMovieComments(movieId).map { response ->
            response.results.map { movieCommentEntityMapper.mapToDomain(it) }
        }
    }

    override fun getMovieRecommendations(movieId: Int, page: Int): Single<List<Movie>> {
        return movieApi.getMovieRecommendations(movieId, page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }
}
