package com.shub39.reflect.reflect.presentation.reflect_list

import java.time.LocalDate
import java.time.LocalTime

data class ReflectUI (
    val id: Long,
    val title: String,
    val description: String,
    val start: LocalDate,
    val reminder: LocalTime? = null,
    val lastUpdated: LocalDate = start,
    val preview: Map<LocalDate, String> = emptyMap()
)