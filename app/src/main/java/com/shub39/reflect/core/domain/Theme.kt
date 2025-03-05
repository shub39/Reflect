package com.shub39.reflect.core.domain

import androidx.compose.ui.graphics.Color
import com.materialkolor.PaletteStyle

data class Theme(
    val seedColor: Color = Color.White,
    val appThemePref: AppTheme = AppTheme.SYSTEM,
    val withAmoled: Boolean = false,
    val style: PaletteStyle = PaletteStyle.TonalSpot,
    val materialTheme: Boolean = false,
    val font: Fonts = Fonts.POPPINS
)
