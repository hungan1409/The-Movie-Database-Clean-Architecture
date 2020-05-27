package com.example.moviedatabase.ui.home

import com.example.moviedatabase.base.BaseViewModel
import com.example.moviedatabase.domain.usecase.movie.*
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
    private val getMovieRecommendationsUseCase: GetMovieRecommendationsUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val movieItemMapper: MovieItemMapper,
    private val genreItemMapper: GenreItemMapper
) : BaseViewModel() {
    val moviesRecommendations = SingleLiveData<List<MovieItem>>()
    val moviesGenres = SingleLiveData<List<GenreItem>>()
    val moviesPopulars = SingleLiveData<List<MovieItem>>()
    val moviesTopRated = SingleLiveData<List<MovieItem>>()
    val moviesUpcoming = SingleLiveData<List<MovieItem>>()
    val movieId = SingleLiveData<Int>().apply { value = -1 }

    var pageRecommendations = 1
    var pagePopulars = 1
    var pageTopRated = 1
    var pageUpcoming = 1

    fun getGenres() {
        if (isLoading() == false) {
            showLoading()
        }
        getGenresUseCase.createObservable()
            .compose(RxUtils.applySingleScheduler()).map {
                it.map { genre ->
                    genreItemMapper.mapToPresentation(genre)
                }
            }.subscribe({
                hideLoading()
                moviesGenres.value = it
            }, {
                hideLoading()
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
                val listMovie = ArrayList<MovieItem>()
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
                val listMovie = ArrayList<MovieItem>()
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
                val listMovie = ArrayList<MovieItem>()
                listMovie.addAll(moviesTopRated.value ?: emptyList())
                listMovie.addAll(it)
                moviesTopRated.value = listMovie
                hideLoading()
            }, {
                hideLoading()
                setThrowable(it)
            }).add(this)
    }

    fun getMovieRecommendations(page: Int) {
        if (movieId.value == -1) {
            return
        }
        getMovieRecommendationsUseCase.createObservable(
            GetMovieRecommendationsUseCase.Params(
                movieId.value!!, page
            )
        )
            .compose(RxUtils.applySingleScheduler())
            .map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }
            .subscribe({
                val listMovie = ArrayList<MovieItem>()
                listMovie.addAll(moviesRecommendations.value ?: emptyList())
                listMovie.addAll(it)
                moviesRecommendations.value = listMovie
            }, {
                setThrowable(it)
            }).add(this)
    }

    fun refreshMoviePage() {
        pageRecommendations = 1
        pagePopulars = 1
        pageTopRated = 1
        pageUpcoming = 1

        moviesRecommendations.value = emptyList()
        moviesPopulars.value = emptyList()
        moviesTopRated.value = emptyList()
        moviesUpcoming.value = emptyList()
    }
}
