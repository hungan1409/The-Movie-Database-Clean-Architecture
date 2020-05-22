package com.example.moviedatabase.di.builder

import android.content.Context
import com.example.moviedatabase.MainApplication
import com.example.moviedatabase.data.di.NetworkModule
import com.example.moviedatabase.data.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, FragmentBuildersModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerContext(application: MainApplication): Context {
        return application.applicationContext
    }
}