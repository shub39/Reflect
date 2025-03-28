package com.shub39.reflect.reflect.presentation.reflect_page

import com.shub39.reflect.reflect.domain.Reflect

sealed interface ReflectPageAction {
    data class OnEdit(val reflect: Reflect): ReflectPageAction
    data class OnDelete(val id: Long): ReflectPageAction
    data object OnPlay: ReflectPageAction
}