package com.shub39.reflect.ui.page.reflect_page

import androidx.compose.runtime.Immutable
import com.shub39.reflect.data.Reflect

@Immutable
data class ReflectPageState (
    val reflect: Reflect? = null,
    val filePaths: List<String> = emptyList()
)