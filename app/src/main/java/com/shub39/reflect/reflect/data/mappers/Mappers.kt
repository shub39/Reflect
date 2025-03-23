package com.shub39.reflect.reflect.data.mappers

import com.shub39.reflect.reflect.data.database.ReflectEntity
import com.shub39.reflect.reflect.data.database.ReflectImageEntity
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.domain.ReflectImage

fun Reflect.toReflectEntity(): ReflectEntity {
    return ReflectEntity(
        id = id,
        title = title,
        description = description,
        reminder = reminder,
        daysOfWeek = daysOfWeek,
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
        lastUpdated = lastUpdated,
        daysOfWeek = daysOfWeek
    )
}

fun ReflectImage.toReflectImageEntity(): ReflectImageEntity {
    return ReflectImageEntity(
        id = id,
        refId = refId,
        comment = comment,
        isFav = isFav,
        timestamp = timestamp,
        imageUri = imageUri
    )
}

fun ReflectImageEntity.toReflectImage(): ReflectImage {
    return ReflectImage(
        id = id,
        refId = refId,
        comment = comment,
        isFav = isFav,
        timestamp = timestamp,
        imageUri = imageUri
    )
}