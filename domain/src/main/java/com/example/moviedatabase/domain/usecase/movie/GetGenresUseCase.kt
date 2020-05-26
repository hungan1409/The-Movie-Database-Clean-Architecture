package com.example.moviedatabase.domain.usecase.movie

import com.example.moviedatabase.domain.model.EmptyParams
import com.example.moviedatabase.domain.model.Genre
import com.example.moviedatabase.domain.repository.MovieRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<EmptyParams, Single<List<Genre>>>() {

    override fun createObservable(params: EmptyParams?): Single<List<Genre>> {
        return movieRepository.getMovieGenres()
    }
}
