package com.shub39.reflect.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val FILE_NAME = "settings.pb"

class ReflectDataStore(
    private val context: Context
) {
    private val tag = "ReflectDataStore"
    private val Context.datastore by preferencesDataStore(name = FILE_NAME)

    private val appVersion = stringPreferencesKey("app_version")

    fun getAppVersion(): Flow<String> = context.datastore.data
        .map { settings ->
            settings[appVersion] ?: ""
        }

    suspend fun updateAppVersion(version: String) {
        context.datastore.edit { settings ->
            settings[appVersion] = version
        }
    }
}