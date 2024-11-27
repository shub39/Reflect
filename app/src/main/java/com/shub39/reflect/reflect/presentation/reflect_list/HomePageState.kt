package com.shub39.reflect.reflect.presentation.reflect_list

import androidx.compose.runtime.Immutable

@Immutable
data class HomePageState (
    val reflects: List<ReflectUI> = emptyList()
)