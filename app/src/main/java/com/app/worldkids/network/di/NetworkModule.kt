package com.app.worldkids.network.di

import com.app.worldkids.network.client.NetworkClient
import com.app.worldkids.network.createHttpClient
import com.app.worldkids.network.logging.Level
import com.app.worldkids.network.logging.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.internal.platform.Platform
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            val interceptor = with(LoggingInterceptor.Builder()) {
                loggable(true)
                setLevel(Level.BODY)
                log(Platform.INFO)
                request("App_request")
                response("App_response")
                build()
            }
            single { createHttpClient(enableLogging, interceptor as Interceptor, get()) }
            single { NetworkClient(httpClient = get(), coroutineDispatchers = get()) }
        }
    }