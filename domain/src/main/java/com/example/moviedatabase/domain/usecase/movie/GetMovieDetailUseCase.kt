package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.MovieDetail
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetMovieDetailUseCase.Params, Single<MovieDetail>>() {

    override fun createObservable(params: Params?): Single<MovieDetail> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getMovieDetail(params.movieId)
        }
    }

    data class Params(val movieId: Int)
}
