package com.shub39.reflect.domain

import org.jetbrains.compose.resources.StringResource
import reflect.app.generated.resources.Res
import reflect.app.generated.resources.dark
import reflect.app.generated.resources.light
import reflect.app.generated.resources.system

enum class AppTheme(val stringResource: StringResource) {
    SYSTEM(Res.string.system),
    LIGHT(Res.string.light),
    DARK(Res.string.dark)
}