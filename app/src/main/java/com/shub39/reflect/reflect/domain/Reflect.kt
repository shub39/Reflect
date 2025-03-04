package com.shub39.reflect.reflect.domain

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Reflect(
    val id: Long = 0,
    val title: String,
    val description: String,
    val reminder: LocalTime?,
    val daysOfWeek: List<DayOfWeek>,
    val start: LocalDate,
    val lastUpdated: LocalDate
)