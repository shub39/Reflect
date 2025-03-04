package com.shub39.reflect.reflect.data.database

import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "images",
    foreignKeys = [
        ForeignKey(
            entity = ReflectEntity::class,
            parentColumns = ["id"],
            childColumns = ["refId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["refId"])]
)
data class ReflectImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val refId: Long,
    val comment: String,
    val isFav: Boolean,
    val timestamp: LocalDateTime,
    val imageUri: Uri
)
