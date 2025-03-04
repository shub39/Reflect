package com.shub39.reflect.reflect.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Entity(tableName = "reflect")
data class ReflectEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val reminder: LocalTime? = null,
    val daysOfWeek: List<DayOfWeek>,
    val start: LocalDate,
    val lastUpdated: LocalDate = start
)