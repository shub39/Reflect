package com.shub39.reflect.reflect.presentation.reflect_list

import com.shub39.reflect.reflect.domain.Reflect

sealed interface ReflectListAction {
    data class OnAddReflect(val reflect: Reflect): ReflectListAction
    data class OnChangeReflect(val reflectId: Long): ReflectListAction
}