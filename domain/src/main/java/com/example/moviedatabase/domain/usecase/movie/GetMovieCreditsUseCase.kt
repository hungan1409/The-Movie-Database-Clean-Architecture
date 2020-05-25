package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.MovieCredits
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetMovieCreditsUseCase.Params, Single<MovieCredits>>() {

    override fun createObservable(params: Params?): Single<MovieCredits> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getCredits(params.movieId)
        }
    }

    data class Params(val movieId: Int)
}
