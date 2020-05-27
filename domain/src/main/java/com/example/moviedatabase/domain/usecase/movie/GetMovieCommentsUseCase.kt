package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.MovieComment
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieCommentsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetMovieCommentsUseCase.Params, Single<List<MovieComment>>>() {

    override fun createObservable(params: Params?): Single<List<MovieComment>> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getMovieComments(params.movieId)
        }
    }

    data class Params(val movieId: Int)
}
