package com.app.worldkids.data.repository

import com.app.worldkids.model.ListMode
import com.app.worldkids.network.client.NetworkClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NetworkRepositoryImpl: NetworkRepository, KoinComponent {

    private val pokemonClient by inject<NetworkClient>()

    override suspend fun register() {
        pokemonClient.register()
    }
    override suspend fun getWordKidsList(): Result<List<ListMode>> {
        return try {
            val response = pokemonClient.getPokemonList(page = 1)
            response.results.forEach { pokemon ->
               // pokemonDao.insert(pokemon.toPokemonEntity(page))
            }

            Result.success(listOf())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}