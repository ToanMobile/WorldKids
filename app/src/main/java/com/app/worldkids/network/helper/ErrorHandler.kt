package com.app.worldkids.network.helper

import com.app.worldkids.network.errors.NetworkError
import com.app.worldkids.network.errors.NetworkException
import com.app.worldkids.utils.DefaultCoroutineDispatchers
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend inline fun <reified T> handleErrors(
    coroutineDispatchers: DefaultCoroutineDispatchers,
    crossinline response: suspend () -> HttpResponse
): T = withContext(coroutineDispatchers.io) {

    val result = try {
        response()
    } catch (e: IOException) {
        Timber.e("handleErrors:Exception1111:$e")
        throw NetworkException(NetworkError.ServiceUnavailable)
    }
    when (result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw NetworkException(NetworkError.ClientError)
        500 -> throw NetworkException(NetworkError.ServerError)
        else -> throw NetworkException(NetworkError.UnknownError)
    }
    try {
        result.body()
    } catch (e: Exception) {
        Timber.e("handleErrors:Exception:$e")
        throw NetworkException(NetworkError.ServerError)
    }
}