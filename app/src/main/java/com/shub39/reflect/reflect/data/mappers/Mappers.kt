package com.shub39.reflect.reflect.data.mappers

import com.shub39.reflect.reflect.data.database.ReflectEntity
import com.shub39.reflect.reflect.domain.Reflect

fun Reflect.toReflectEntity(): ReflectEntity {
    return ReflectEntity(
        id = id,
        title = title,
        description = description,
        reminder = reminder,
        start = start,
        lastUpdated = lastUpdated
    )
}

fun ReflectEntity.toReflect(): Reflect {
    return Reflect(
        id = id,
        title = title,
        description = description,
        reminder = reminder,
        start = start,
        lastUpdated = lastUpdated
    )
}