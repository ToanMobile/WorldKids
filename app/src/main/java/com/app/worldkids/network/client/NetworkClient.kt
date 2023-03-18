package com.app.worldkids.network.client

import com.app.worldkids.model.ListMode
import com.app.worldkids.model.RegisterRequest
import com.app.worldkids.network.NetworkConstants
import com.app.worldkids.network.helper.handleErrors
import com.app.worldkids.network.model.PokemonResponse
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

class NetworkClient(
    private val httpClient: HttpClient,
    private val coroutineDispatchers: DefaultCoroutineDispatchers
) {

    suspend fun register() {
        return handleErrors(coroutineDispatchers) {
            httpClient.post(NetworkConstants.Wordkids.register) {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(RegisterRequest(deviceToken = getAndroidID, image = "")))
            }
        }
    }

    suspend fun getPokemonList(
        page: Long,
    ): PokemonResponse {
        return handleErrors(coroutineDispatchers) {
            httpClient.get(NetworkConstants.Wordkids.route) {
                url {
                    parameters.append("limit", PageSize.toString())
                    parameters.append("offset", (page * PageSize).toString())
                }
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