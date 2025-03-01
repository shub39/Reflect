package com.shub39.reflect.core.domain

import kotlinx.coroutines.flow.Flow

// Main datastore interface
interface PrefDatastore {
    fun getAppTheme(): Flow<Theme>
    suspend fun setAppTheme(theme: Theme)
}