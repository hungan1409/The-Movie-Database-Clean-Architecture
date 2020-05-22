package com.example.moviedatabase.di.builder

import com.example.moviedatabase.MainActivity
import com.example.moviedatabase.ui.contributor.ContributorFragment
import com.example.moviedatabase.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeRepoDetailFragment(): ContributorFragment
}
