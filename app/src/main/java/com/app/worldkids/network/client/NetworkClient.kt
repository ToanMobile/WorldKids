package com.app.worldkids.network.client

import com.app.worldkids.model.BaseResponse
import com.app.worldkids.model.CheckInStatus
import com.app.worldkids.model.request.ChangeStatusRequest
import com.app.worldkids.model.request.RegisterRequest
import com.app.worldkids.model.response.ListUser
import com.app.worldkids.model.response.Register
import com.app.worldkids.network.NetworkConstants
import com.app.worldkids.network.helper.handleErrors
import com.app.worldkids.utils.DefaultCoroutineDispatchers
import com.app.worldkids.utils.getAndroidID
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.plugin
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

    fun updateAuth(token: String) = httpClient.plugin(Auth).bearer {
        loadTokens {
            Timber.e("loadTokens::$token")
            BearerTokens(accessToken = token, refreshToken = token)
        }
    }

    suspend fun register() : BaseResponse<Register> {
        return handleErrors(coroutineDispatchers) {
            httpClient.post(NetworkConstants.Wordkids.register) {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(RegisterRequest(deviceId = getAndroidID)))
            }
        }
    }

    suspend fun getListCheckIn(classId: String): BaseResponse<ListUser> {
        return handleErrors(coroutineDispatchers) {
            httpClient.get(NetworkConstants.Wordkids.listCheckIn(classId = classId)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun statusReport(classId: String): BaseResponse<CheckInStatus> {
        return handleErrors(coroutineDispatchers) {
            httpClient.get(NetworkConstants.Wordkids.statusReport(classId = classId, type = "CHECKIN")) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun changeStatus(clientId: String, status: String) {
        httpClient.post(NetworkConstants.Wordkids.changeStatus) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(ChangeStatusRequest(clientId = clientId, status = status)))
        }
    }
}