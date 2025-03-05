package com.shub39.reflect.core.domain

import androidx.compose.ui.graphics.Color
import com.materialkolor.PaletteStyle
import kotlinx.coroutines.flow.Flow

// Main datastore interface
interface PrefDatastore {
    fun getAppTheme(): Flow<AppTheme>
    suspend fun setAppTheme(appTheme: AppTheme)

    fun getSeedColor(): Flow<Color>
    suspend fun setSeedColor(color: Color)

    fun getAmoledPref(): Flow<Boolean>
    suspend fun setAmoled(pref: Boolean)

    fun getStyle(): Flow<PaletteStyle>
    suspend fun setStyle(style: PaletteStyle)

    fun getMaterialThemePref(): Flow<Boolean>
    suspend fun setMaterialTheme(pref: Boolean)

    fun getFont(): Flow<Fonts>
    suspend fun setFont(font: Fonts)
}