package com.shub39.reflect.ui.page.reflect_list

import androidx.compose.runtime.Immutable

@Immutable
data class HomePageState (
    val reflects: List<ReflectUI> = emptyList()
)