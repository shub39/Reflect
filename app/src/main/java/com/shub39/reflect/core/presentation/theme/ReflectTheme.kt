package com.shub39.reflect.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.materialkolor.DynamicMaterialTheme
import com.shub39.reflect.core.domain.AppTheme
import com.shub39.reflect.core.domain.Theme

@Composable
fun ReflectTheme(
    theme: Theme,
    content: @Composable () -> Unit
) = DynamicMaterialTheme(
    seedColor = if (theme.materialTheme) {
        theme.seedColor
    } else {
        colorResource(android.R.color.system_accent1_200)
    },
    useDarkTheme = when (theme.appThemePref) {
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
        AppTheme.SYSTEM -> isSystemInDarkTheme()
    },
    withAmoled = theme.withAmoled,
    typography = provideTypography(font = theme.font.fontId),
    content = content
)