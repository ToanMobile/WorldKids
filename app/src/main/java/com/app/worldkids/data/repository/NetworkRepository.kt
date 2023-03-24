package com.app.worldkids.data.repository

import com.app.worldkids.model.CheckInStatus
import com.app.worldkids.model.response.ListUser

interface NetworkRepository {

    suspend fun register() : Result<Boolean>

    suspend fun getListCheckIn(classId : String): Result<ListUser>

    suspend fun statusReport(classId : String): Result<CheckInStatus>

    suspend fun changeStatus(clientId: String, status: String): Result<Boolean>
}