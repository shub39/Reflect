package com.shub39.reflekt.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.materialkolor.DynamicMaterialTheme
import com.shub39.reflekt.domain.AppTheme
import com.shub39.reflekt.domain.Theme

@Composable
fun ReflectTheme(
    theme: Theme = Theme(),
    content: @Composable () -> Unit
) {
    DynamicMaterialTheme(
        seedColor = theme.seedColor,
        withAmoled = theme.withAmoled,
        useDarkTheme = when (theme.appTheme) {
            AppTheme.LIGHT -> false
            AppTheme.DARK -> true
            AppTheme.SYSTEM -> isSystemInDarkTheme()
        },
        typography = provideTypography(),
        style = theme.style,
        content = content
    )
}