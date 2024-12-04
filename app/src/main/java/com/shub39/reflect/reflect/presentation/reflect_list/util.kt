package com.shub39.reflect.reflect.presentation.reflect_list

import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.presentation.getFilePathsWithDates

fun Reflect.toReflectUi(): ReflectUI {
    return ReflectUI(
        id = id,
        title = title,
        description = description,
        start = start,
        reminder = reminder,
        preview = getFilePathsWithDates(id.toString()),
    )
}

fun ReflectUI.toReflect(): Reflect {
    return Reflect(
        id = id,
        title = title,
        description = description,
        reminder = reminder,
        start = start
    )
}