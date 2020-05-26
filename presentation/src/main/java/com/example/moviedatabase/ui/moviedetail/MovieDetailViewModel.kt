package com.example.moviedatabase.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.moviedatabase.base.BaseViewModel
import com.example.moviedatabase.domain.usecase.movie.GetMovieCreditsUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMovieDetailUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMovieRecommendationsUseCase
import com.example.moviedatabase.domain.usecase.movie.GetMovieVideosUseCase
import com.example.moviedatabase.extension.add
import com.example.moviedatabase.extension.toMMMMyyyy
import com.example.moviedatabase.model.*
import com.example.moviedatabase.util.RxUtils
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieVideosUseCase: GetMovieVideosUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val movieDetailItemMapper: MovieDetailItemMapper,
    private val movieVideosItemMapper: MovieVideosItemMapper,
    private val movieCreditsItemMapper: MovieCreditsItemMapper,
    private val getMovieRecommendationsUseCase: GetMovieRecommendationsUseCase,
    private val movieItemMapper: MovieItemMapper
) : BaseViewModel() {

    val movieDetailItem = MutableLiveData<MovieDetailItem>()

    val releaseDate = Transformations.map(movieDetailItem) {
        it.releaseDate?.toMMMMyyyy()
    }
    val genres: LiveData<List<String>> = Transformations.map(movieDetailItem) {
        it.genres?.map { genre -> (genre.name ?: "") } ?: listOf()
    }
    val isExpandingOverview = MutableLiveData<Boolean>().apply {
        value = false
    }

    val rating = MutableLiveData<Float>().apply {
        value = 0.0f
    }

    val movieVideosItem = MutableLiveData<MovieVideosItem>()

    val movieCreditsItem = MutableLiveData<MovieCreditsItem>()

    val movieRecommendations = MutableLiveData<List<MovieItem>>()

    fun expandOverview() {
        isExpandingOverview.value = isExpandingOverview.value?.not()
    }

    fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase.createObservable(GetMovieDetailUseCase.Params(movieId))
            .compose(RxUtils.applySingleScheduler())
            .map { movieDetailItemMapper.mapToPresentation(it) }
            .subscribe({
                movieDetailItem.value = it
            }, {
                setThrowable(it)
            }).add(this)
    }

    fun getMovieVideos(movieId: Int) {
        getMovieVideosUseCase.createObservable(GetMovieVideosUseCase.Params(movieId))
            .compose(RxUtils.applySingleScheduler())
            .map { movieVideosItemMapper.mapToPresentation(it) }
            .subscribe({
                movieVideosItem.value = it
            }, {
                setThrowable(it)
            }).add(this)
    }

    fun getMovieCredits(movieId: Int) {
        getMovieCreditsUseCase.createObservable(GetMovieCreditsUseCase.Params(movieId))
            .compose(RxUtils.applySingleScheduler())
            .map { movieCreditsItemMapper.mapToPresentation(it) }
            .subscribe({
                movieCreditsItem.value = it
            }, {
                setThrowable(it)
            }).add(this)
    }

    fun getMovieRecommendations(movieId: Int) {
        getMovieRecommendationsUseCase.createObservable(
            GetMovieRecommendationsUseCase.Params(
                movieId
            )
        )
            .compose(RxUtils.applySingleScheduler())
            .map {
                it.map { movieItem ->
                    movieItemMapper.mapToPresentation(movieItem)
                }
            }
            .subscribe({
                var listMovie = ArrayList<MovieItem>()
                listMovie.addAll(movieRecommendations.value ?: emptyList())
                listMovie.addAll(it)
                movieRecommendations.value = listMovie
            }, {
                setThrowable(it)
            }).add(this)
    }
}

