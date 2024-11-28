package com.shub39.reflect.reflect.presentation.reflect_page

import androidx.compose.runtime.Immutable
import com.shub39.reflect.reflect.domain.Reflect

@Immutable
data class ReflectPageState (
    val reflect: Reflect? = null,
    val filePaths: List<String> = emptyList(),
    val isGenerating: Boolean = false,
    val outputPath: String? = null,
    val error: Int? = null
)