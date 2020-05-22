package com.example.moviedatabase.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedatabase.ViewModelProviderFactory
import com.example.moviedatabase.di.annotation.ViewModelKey
import com.example.moviedatabase.ui.contributor.ContributorViewModel
import com.example.moviedatabase.ui.main.MainViewModel
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
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContributorViewModel::class)
    abstract fun bindRepoDetailViewModel(contributorViewModel: ContributorViewModel): ViewModel
}