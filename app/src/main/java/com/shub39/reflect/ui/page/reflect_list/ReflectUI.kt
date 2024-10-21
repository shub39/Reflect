package com.shub39.reflect.ui.page.reflect_list

import java.time.LocalDate
import java.time.LocalTime

data class ReflectUI (
    val id: Long,
    val title: String,
    val description: String,
    val start: LocalDate,
    val reminder: LocalTime? = null,
    val lastUpdated: LocalDate = start,
    val preview: List<String> = emptyList()
)