package com.app.worldkids.data.repository

import com.app.worldkids.data.pre.DataStoreUtils
import com.app.worldkids.model.response.ListUser
import com.app.worldkids.network.client.NetworkClient
import org.koin.core.component.KoinComponent
import timber.log.Timber

class NetworkRepositoryImpl(private val networkClient: NetworkClient, private val dataStoreUtils: DataStoreUtils) : NetworkRepository, KoinComponent {

    override suspend fun register(): Result<Boolean> {
        return try {
            val response = networkClient.register()
            response.data?.let {
                dataStoreUtils.updateUserPreferences(userPreferences = it)
                Timber.e("register::::${it}")
            }
            Result.success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getListCheckIn(classId: String): Result<ListUser> {
        return try {
            val response = networkClient.getListCheckIn(classId = classId)
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