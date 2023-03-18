package com.app.worldkids.network.di

import com.app.worldkids.network.client.NetworkClient
import com.app.worldkids.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { NetworkClient(httpClient = get()) }
    }
}