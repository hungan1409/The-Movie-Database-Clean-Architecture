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

    var pagePopulars = 1
    var pageTopRated = 1
    var pageUpcoming = 1

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
        if (isLoading() == false) {
            showLoading()
        }
        getMoviePopularUseCase.createObservable(GetMoviePopularUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                var listMovie = ArrayList<MovieItem>()
                listMovie.addAll(moviesPopulars.value ?: emptyList())
                listMovie.addAll(it)
                moviesPopulars.value = listMovie
                hideLoading()
            }, {
                hideLoading()
                setThrowable(it)
            }).add(this)

    }

    fun getMovieListUpcoming(page: Int) {
        if (isLoading() == false) {
            showLoading()
        }
        getMovieUpcomingUseCase.createObservable(GetMovieUpcomingUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                var listMovie = ArrayList<MovieItem>()
                listMovie.addAll(moviesUpcoming.value ?: emptyList())
                listMovie.addAll(it)
                moviesUpcoming.value = listMovie
                hideLoading()
            }, {
                hideLoading()
                setThrowable(it)
            }).add(this)

    }

    fun getMovieListTopRated(page: Int) {
        if (isLoading() == false) {
            showLoading()
        }
        getMovieTopRatedUseCase.createObservable(GetMovieTopRatedUseCase.Params(page))
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }.subscribe({
                var listMovie = ArrayList<MovieItem>()
                listMovie.addAll(moviesTopRated.value ?: emptyList())
                listMovie.addAll(it)
                moviesTopRated.value = listMovie
                hideLoading()
            }, {
                hideLoading()
                setThrowable(it)
            }).add(this)
    }

    fun refreshMoviePage() {
        pagePopulars = 1
        pageTopRated = 1
        pageUpcoming = 1
        moviesPopulars.value = emptyList()
        moviesTopRated.value = emptyList()
        moviesUpcoming.value = emptyList()
    }
}
