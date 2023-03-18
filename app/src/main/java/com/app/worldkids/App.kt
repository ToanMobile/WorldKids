package com.app.worldkids

import android.app.Application
import com.app.worldkids.data.di.dataModule
import com.app.worldkids.data.di.viewModelModule
import com.app.worldkids.network.di.networkModule
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            modules(
                dataModule,
                networkModule(BuildConfig.DEBUG),
                viewModelModule
            )
        }
    }
}