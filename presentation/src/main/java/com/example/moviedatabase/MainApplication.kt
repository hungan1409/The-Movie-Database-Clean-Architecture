package com.example.moviedatabase

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.example.moviedatabase.crashlytics.CrashlyticsTree
import com.example.moviedatabase.data.BuildConfig
import com.example.moviedatabase.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        val crashlytics = CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG)
            .build()
        Fabric.with(
            this,
            Crashlytics.Builder().core(crashlytics).build()
        )

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree())
    }
}
