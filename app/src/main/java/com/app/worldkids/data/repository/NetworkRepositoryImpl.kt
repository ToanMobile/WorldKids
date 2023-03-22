package com.app.worldkids.data.repository

import com.app.worldkids.data.DataStoreUtils
import com.app.worldkids.model.ListMode
import com.app.worldkids.model.response.ListUser
import com.app.worldkids.network.client.NetworkClient
import org.koin.core.component.KoinComponent
import timber.log.Timber

class NetworkRepositoryImpl(private val networkClient: NetworkClient, private val dataStoreUtils: DataStoreUtils) : NetworkRepository, KoinComponent {

    override suspend fun register(): Result<Boolean> {
        return try {
            val response = networkClient.register()
            response.data?.auth?.token?.let {
                dataStoreUtils.updateToken(token = it)
            }
            Timber.e("register" + response.data.toString())
            Result.success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getListCheckIn(): Result<ListUser> {
        return try {
            val response = networkClient.getListCheckIn(classId = "72")
            Timber.e("getListCheckIn::" + response.data.toString())
            response.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}