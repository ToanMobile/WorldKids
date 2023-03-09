package com.app.presentation

import android.app.Application
import com.app.data.di.koinDataSourceModules
import com.app.domain.di.koinDomainModules
import com.app.presentation.di.koinPresentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Copyright (C) 2023 ToanMobile
 * Licensed under the Apache License Version 2.0
 * Android Main Application
 */
class AndroidApplication : Application() {

    override fun onCreate() {
        // Koin
        startKoin {
            androidContext(this@AndroidApplication)
            modules(koinDataSourceModules)
            modules(koinDomainModules)
            modules(koinPresentationModules)
        }
        super.onCreate()
    }
}
