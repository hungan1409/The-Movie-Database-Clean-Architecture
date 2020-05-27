package com.example.moviedatabase.di.builder

import com.example.moviedatabase.MainActivity
import com.example.moviedatabase.ui.home.HomeFragment
import com.example.moviedatabase.ui.moviedetail.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}
