package com.shub39.reflect.reflect.presentation.settings

import androidx.compose.ui.graphics.Color
import com.materialkolor.PaletteStyle
import com.shub39.reflect.core.domain.AppTheme
import com.shub39.reflect.core.domain.Fonts

sealed interface SettingsPageAction {
    data class OnUpdateSeedColor(val color: Color): SettingsPageAction
    data class OnUpdateAppTheme(val appTheme: AppTheme): SettingsPageAction
    data class OnUpdateStyle(val style: PaletteStyle): SettingsPageAction
    data class OnUpdateMaterialPref(val pref: Boolean): SettingsPageAction
    data class OnUpdateFont(val font: Fonts): SettingsPageAction
}