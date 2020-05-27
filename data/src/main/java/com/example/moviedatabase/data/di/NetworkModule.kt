package com.example.moviedatabase.data.di

import com.example.moviedatabase.data.remote.api.MovieApi
import com.example.moviedatabase.data.remote.builder.RetrofitBuilder
import com.example.moviedatabase.data.remote.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: RetrofitBuilder,
        headerInterceptor: HeaderInterceptor
    ): Retrofit = retrofitBuilder
        .addInterceptors(headerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
}
