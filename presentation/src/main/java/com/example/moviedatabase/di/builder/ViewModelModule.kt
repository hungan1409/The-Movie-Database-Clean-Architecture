package com.example.moviedatabase.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedatabase.ViewModelProviderFactory
import com.example.moviedatabase.di.annotation.ViewModelKey
import com.example.moviedatabase.ui.home.HomeViewModel
import com.example.moviedatabase.ui.moremovies.MoreMoviesViewModel
import com.example.moviedatabase.ui.moviedetail.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreMoviesViewModel::class)
    abstract fun bindMoreMoviesViewModel(moreMoviesViewModel: MoreMoviesViewModel): ViewModel
}
