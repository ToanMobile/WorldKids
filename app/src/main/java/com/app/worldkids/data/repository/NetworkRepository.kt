package com.app.worldkids.data.repository

import com.app.worldkids.model.ListMode

interface NetworkRepository {

    suspend fun register() : Result<Boolean>

    suspend fun getListCheckIn(): Result<List<ListMode>>

}