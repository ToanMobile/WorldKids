package com.app.worldkids.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.app.worldkids.data.DataStoreUtils
import com.app.worldkids.model.ListMode
import com.app.worldkids.network.client.NetworkClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber
import java.util.prefs.Preferences

class NetworkRepositoryImpl(private val networkClient: NetworkClient, private val dataStoreUtils: DataStoreUtils) : NetworkRepository, KoinComponent {

    override suspend fun register(): Result<Boolean> {
        return try {
            val response = networkClient.register()
            Timber.e("register::$response")
            response.data?.auth?.token?.let {
                Timber.e("register::$it")
                dataStoreUtils.updateToken(token = it)
            }
            Timber.e(response.data.toString())
            Result.success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getWordKidsList(): Result<List<ListMode>> {
        return try {
            val response = networkClient.getPokemonList(page = 1)
//            response.results.forEach { pokemon ->
//               // pokemonDao.insert(pokemon.toPokemonEntity(page))
//            }

            Result.success(listOf())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}