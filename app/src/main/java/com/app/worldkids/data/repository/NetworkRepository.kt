package com.app.worldkids.data.repository

import com.app.worldkids.model.ListMode

interface NetworkRepository {

    suspend fun register()
    suspend fun getWordKidsList(): Result<List<ListMode>>

}