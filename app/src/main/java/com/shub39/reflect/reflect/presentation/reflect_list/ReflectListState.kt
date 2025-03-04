package com.shub39.reflect.reflect.presentation.reflect_list

import androidx.compose.runtime.Immutable
import com.shub39.reflect.reflect.domain.Reflect

@Immutable
data class ReflectListState (
    val reflects: List<Reflect> = emptyList()
)