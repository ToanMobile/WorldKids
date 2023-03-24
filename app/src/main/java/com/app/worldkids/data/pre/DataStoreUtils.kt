package com.app.worldkids.data.pre

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.app.worldkids.data.pre.serializer.RegisterSerializer
import com.app.worldkids.model.response.Register
import kotlinx.coroutines.flow.firstOrNull

private val Context.dataStorePreferences by dataStore(fileName = "UserPreferencesDataStore", serializer = RegisterSerializer)

class DataStoreUtils(private val context: Context) {
    private val dataStoreUserPreferences: DataStore<Register> get() = context.dataStorePreferences

    suspend fun getUserPreferences() = dataStoreUserPreferences.data.firstOrNull()

    suspend fun updateUserPreferences(userPreferences: Register) {
        dataStoreUserPreferences.updateData { userPreferences }
    }

    suspend fun clearPreferences() {
        updateUserPreferences(userPreferences = Register())
    }
}