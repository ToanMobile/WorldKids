package com.app.worldkids.data.repository

import com.app.worldkids.model.CheckInStatus
import com.app.worldkids.model.response.ListUser
import com.app.worldkids.model.response.Register

interface NetworkRepository {

    suspend fun register() : Result<Register?>

    suspend fun getListCheckIn(classId : String): Result<ListUser>

    suspend fun statusReport(classId : String): Result<CheckInStatus>

    suspend fun changeStatus(clientId: String, status: String): Result<Boolean>

    suspend fun verify(classId: String): Result<Boolean>
}