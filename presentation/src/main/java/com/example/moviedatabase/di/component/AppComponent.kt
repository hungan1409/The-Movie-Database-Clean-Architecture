package com.example.moviedatabase.di.component

import com.example.moviedatabase.MainApplication
import com.example.moviedatabase.di.builder.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}