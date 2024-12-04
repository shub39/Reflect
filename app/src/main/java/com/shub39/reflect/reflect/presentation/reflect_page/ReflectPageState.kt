package com.shub39.reflect.reflect.presentation.reflect_page

import androidx.compose.runtime.Immutable
import com.shub39.reflect.reflect.domain.Reflect
import java.time.LocalDate

@Immutable
data class ReflectPageState (
    val reflect: Reflect? = null,
    val filePaths: Map<LocalDate, String> = emptyMap(),
    val isGenerating: Boolean = false,
    val outputPath: String? = null,
    val error: Int? = null
)