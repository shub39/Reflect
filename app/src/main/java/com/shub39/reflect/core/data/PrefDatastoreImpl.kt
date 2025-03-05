package com.shub39.reflect.core.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.materialkolor.PaletteStyle
import com.shub39.reflect.core.domain.PrefDatastore
import com.shub39.reflect.core.domain.AppTheme
import com.shub39.reflect.core.domain.Fonts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PrefDatastoreImpl(
    private val datastore: DataStore<Preferences>
): PrefDatastore {

    companion object {
        private val themeKey = stringPreferencesKey("app_theme")
        private val colorKey = intPreferencesKey("seed_color")
        private val amoledKey = booleanPreferencesKey("is_amoled")
        private val styleKey = stringPreferencesKey("palette_style")
        private val materialYouKey = booleanPreferencesKey("material_you")
        private val fontKey = stringPreferencesKey("font")
    }

    override fun getAppTheme(): Flow<AppTheme> = datastore.data.map { prefs ->
        val appTheme = prefs[themeKey] ?: AppTheme.SYSTEM.name
        AppTheme.valueOf(appTheme)
    }
    override suspend fun setAppTheme(appTheme: AppTheme) {
        datastore.edit { it[themeKey] = appTheme.name }
    }

    override fun getSeedColor(): Flow<Color> = datastore.data.map { prefs ->
        Color(prefs[colorKey] ?: Color.White.toArgb())
    }
    override suspend fun setSeedColor(color: Color) {
        datastore.edit { it[colorKey] = color.toArgb() }
    }

    override fun getAmoledPref(): Flow<Boolean> = datastore.data.map { prefs ->
        prefs[amoledKey] ?: false
    }
    override suspend fun setAmoled(pref: Boolean) {
        datastore.edit { it[amoledKey] = pref }
    }

    override fun getStyle(): Flow<PaletteStyle> = datastore.data.map { prefs ->
        val palette = prefs[styleKey] ?: PaletteStyle.TonalSpot.name
        PaletteStyle.valueOf(palette)
    }
    override suspend fun setStyle(style: PaletteStyle) {
        datastore.edit { it[styleKey] = style.name }
    }

    override fun getMaterialThemePref(): Flow<Boolean> = datastore.data.map { prefs ->
        prefs[materialYouKey] ?: false
    }
    override suspend fun setMaterialTheme(pref: Boolean) {
        datastore.edit { it[materialYouKey] = pref }
    }

    override fun getFont(): Flow<Fonts> = datastore.data.map { prefs ->
        val font = prefs[fontKey] ?: Fonts.POPPINS.name
        Fonts.valueOf(font)
    }
    override suspend fun setFont(font: Fonts) {
        datastore.edit { it[fontKey] = font.name }
    }
}