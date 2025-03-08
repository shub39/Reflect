package com.shub39.reflect.core.presentation

import java.util.Locale

fun String.caps(): String {
    return lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}