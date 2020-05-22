package com.example.moviedatabase.data.di

import android.content.Context
import androidx.room.Room
import com.example.moviedatabase.data.Constants
import com.example.moviedatabase.data.ContributorRepositoryImpl
import com.example.moviedatabase.data.ItemRepositoryImpl
import com.example.moviedatabase.data.UserRepositoryImpl
import com.example.moviedatabase.data.local.db.AppDatabase
import com.example.moviedatabase.data.local.pref.AppPrefs
import com.example.moviedatabase.data.local.pref.PrefHelper
import com.example.moviedatabase.domain.repository.ContributorRepository
import com.example.moviedatabase.domain.repository.ItemRepository
import com.example.moviedatabase.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @DatabaseInfo
    fun providerDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerAppPrefs(context: Context): AppPrefs {
        return AppPrefs(context, Gson())
    }

    @Provides
    @Singleton
    fun providerUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providerItemRepository(repository: ItemRepositoryImpl): ItemRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providerContributorRepository(repository: ContributorRepositoryImpl): ContributorRepository {
        return repository
    }
}
