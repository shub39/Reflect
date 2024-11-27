package com.shub39.reflect.reflect.domain

import java.time.LocalDate
import java.time.LocalTime

data class Reflect(
    val id: Long = 0,
    val title: String,
    val description: String,
    val reminder: LocalTime? = null,
    val start: LocalDate,
    val lastUpdated: LocalDate = start
)