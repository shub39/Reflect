package com.shub39.reflect.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.shub39.reflect.core.domain.PrefDatastore
import com.shub39.reflect.core.domain.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PrefDatastoreImpl(
    private val datastore: DataStore<Preferences>
): PrefDatastore {

    companion object {
        private val themeKey = stringPreferencesKey("app_theme")
    }

    override fun getAppTheme(): Flow<Theme> = datastore.data.map { prefs ->
        val theme = prefs[themeKey] ?: Theme.SYSTEM.name
        Theme.valueOf(theme)
    }
    override suspend fun setAppTheme(theme: Theme) {
        datastore.edit { prefs ->
            prefs[themeKey] = theme.name
        }
    }
}