package com.app.worldkids

import android.app.Application
import com.app.worldkids.data.di.dataModule
import com.app.worldkids.data.di.viewModelModule
import com.app.worldkids.network.di.networkModule
import com.app.worldkids.utils.ContextApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        ContextApp.initContext(this)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(androidContext = this@App)
            androidFileProperties()
            modules(
                dataModule,
                networkModule(BuildConfig.DEBUG),
                viewModelModule
            )
        }
    }
}