package com.shub39.reflect.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

class DatastoreFactory(private val context: Context) {
    fun getPreferencesDataStore() : DataStore<Preferences> = createDataStore (
        producePath = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath }
    )

    companion object {
        private const val DATA_STORE_FILE_NAME = "reflect.preferences_pb"
    }
}

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })