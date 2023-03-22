package com.app.worldkids.network

import com.app.worldkids.data.DataStoreUtils
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import timber.log.Timber

internal fun createHttpClient(enableLogging: Boolean, interceptor: Interceptor, dataStoreUtils: DataStoreUtils): HttpClient {
    return HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
            addInterceptor(interceptor)
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                encodeDefaults = false
            })
        }
        install(Auth) {
            lateinit var token: String
            bearer {
                loadTokens {
                    token = dataStoreUtils.getToken()
                    Timber.e("loadTokens::$token")
                    BearerTokens(accessToken = token, refreshToken = token)
                }
            }
        }
        if (enableLogging) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}