package com.shub39.reflect.reflect.domain

import android.net.Uri
import kotlinx.datetime.LocalDateTime

data class ReflectImage(
    val id: Long = 0,
    val refId: Long,
    val comment: String,
    val isFav: Boolean,
    val timestamp: LocalDateTime,
    val imageUri: Uri
)