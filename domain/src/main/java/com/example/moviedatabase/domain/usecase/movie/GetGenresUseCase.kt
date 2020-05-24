package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.Genre
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetGenresUseCase.Params, Single<List<Genre>>>() {

    override fun createObservable(params: Params?): Single<List<Genre>> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getMovieGenres()
        }
    }

    data class Params(val page: Int)
}
