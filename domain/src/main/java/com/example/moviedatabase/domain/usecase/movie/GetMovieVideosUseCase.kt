package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.MovieVideos
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieVideosUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetMovieVideosUseCase.Params, Single<MovieVideos>>() {

    override fun createObservable(params: Params?): Single<MovieVideos> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getMovieVideos(params.movieId)
        }
    }

    data class Params(val movieId: Int)
}
