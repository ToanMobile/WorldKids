package com.app.worldkids.network.client

import com.app.worldkids.model.ListMode
import com.app.worldkids.model.request.RegisterRequest
import com.app.worldkids.model.response.RegisterResponse
import com.app.worldkids.network.NetworkConstants
import com.app.worldkids.network.helper.handleErrors
import com.app.worldkids.network.model.BaseResponse
import com.app.worldkids.utils.DefaultCoroutineDispatchers
import com.app.worldkids.utils.getAndroidID
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

class NetworkClient(
    private val httpClient: HttpClient,
    private val coroutineDispatchers: DefaultCoroutineDispatchers
) {

    suspend fun register() : BaseResponse<RegisterResponse> {
        return handleErrors(coroutineDispatchers) {
            httpClient.post(NetworkConstants.Wordkids.register) {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(RegisterRequest(deviceId = getAndroidID)))
            }
        }
    }

    suspend fun getListCheckIn(classId: String): RegisterResponse {
        return handleErrors(coroutineDispatchers) {
            httpClient.get(NetworkConstants.Wordkids.listCheckIn(classId = classId)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getPokemonByName(
        name: String,
    ): ListMode {
        return handleErrors(coroutineDispatchers) {
            httpClient.get(NetworkConstants.Wordkids.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val PageSize = 20
    }

}