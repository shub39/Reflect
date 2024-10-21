package com.shub39.reflect.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "reflect")
data class Reflect (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val reminder: LocalTime? = null,
    val start: LocalDate,
    val lastUpdated: LocalDate = start
)