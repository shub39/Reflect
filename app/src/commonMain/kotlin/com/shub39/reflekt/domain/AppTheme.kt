package com.shub39.reflekt.domain

import org.jetbrains.compose.resources.StringResource
import reflekt.app.generated.resources.Res
import reflekt.app.generated.resources.dark
import reflekt.app.generated.resources.light
import reflekt.app.generated.resources.system


enum class AppTheme(val stringResource: StringResource) {
    SYSTEM(Res.string.system),
    LIGHT(Res.string.light),
    DARK(Res.string.dark)
}