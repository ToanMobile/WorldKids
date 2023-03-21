/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */
package com.app.worldkids.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStoreWordKids by preferencesDataStore(name = "SDKDataStore")

class DataStoreUtils(private val context: Context) {
    private val dataStorePreferences: DataStore<Preferences> get() = context.dataStoreWordKids

    suspend fun getToken() = dataStorePreferences.data.map {
        it[stringPreferencesKey("TOKEN")]
    }.firstOrNull() ?:""

    suspend fun updateToken(token: String) {
        dataStorePreferences.edit {
            it[stringPreferencesKey("TOKEN")] = token
        }
    }
}
