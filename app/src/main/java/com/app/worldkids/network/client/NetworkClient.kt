package com.app.worldkids.network.client

import com.app.worldkids.model.ListMode
import com.app.worldkids.network.NetworkConstants
import com.app.worldkids.network.helper.handleErrors
import com.app.worldkids.network.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class NetworkClient(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(
        page: Long,
    ): PokemonResponse {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.route) {
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
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val PageSize = 20
    }

}