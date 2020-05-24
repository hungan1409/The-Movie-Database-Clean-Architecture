package com.example.moviedatabase.ui.home

import com.example.moviedatabase.base.BaseViewModel
import com.example.moviedatabase.domain.usecase.movie.GetGenresUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMoviePopularUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMovieTopRatedUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMovieUpcomingUseCase
import com.example.moviedatabase.extension.add
import com.example.moviedatabase.model.GenreItem
import com.example.moviedatabase.model.GenreItemMapper
import com.example.moviedatabase.model.MovieItem
import com.example.moviedatabase.model.MovieItemMapper
import com.example.moviedatabase.util.RxUtils
import com.example.moviedatabase.util.SingleLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val getMovieUpcomingUseCase: GetMovieUpcomingUseCase,
    private val getMovieTopRatedUseCase: GetMovieTopRatedUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val movieItemMapper: MovieItemMapper,
    private val genreItemMapper: GenreItemMapper
) : BaseViewModel() {
    val moviesRecommendations = SingleLiveData<List<MovieItem>>()
    val moviesGenres = SingleLiveData<List<GenreItem>>()
    val moviesPopulars = SingleLiveData<List<MovieItem>>()
    val moviesTopRated = SingleLiveData<List<MovieItem>>()
    val moviesUpcoming = SingleLiveData<List<MovieItem>>()

    fun getGenres() {
        getGenresUseCase.createObservable()
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { genre ->
                    genreItemMapper.mapToPresentation(genre)
                }
            }.subscribe({
                moviesGenres.value = it
            }, {
                setThrowable(it)
            }).add(this)

    }

    fun getMovieListPopular(page: Int) {
        getMoviePopularUseCase.createObservable(GetMoviePopularUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                moviesPopulars.value = it
            }, {
                setThrowable(it)
            }).add(this)

    }

    fun getMovieListUpcoming(page: Int) {
        getMovieUpcomingUseCase.createObservable(GetMovieUpcomingUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                moviesUpcoming.value = it
            }, {
                setThrowable(it)
            }).add(this)

    }

    fun getMovieListTopRated(page: Int) {
        getMovieTopRatedUseCase.createObservable(GetMovieTopRatedUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                moviesTopRated.value = it
            }, {
                setThrowable(it)
            }).add(this)

    }
}
