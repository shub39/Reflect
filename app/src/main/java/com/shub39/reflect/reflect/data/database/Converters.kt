package com.shub39.reflect.reflect.data.database

import android.net.Uri
import androidx.room.TypeConverter
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object Converters {
    @TypeConverter
    fun localTimeToInt(time: LocalTime): Int {
        return time.toSecondOfDay()
    }

    @TypeConverter
    fun intToLocalTime(int: Int): LocalTime {
        return LocalTime.fromSecondOfDay(int)
    }

    @TypeConverter
    fun localDateToInt(date: LocalDate): Int {
        return date.toEpochDays()
    }

    @TypeConverter
    fun intToLocalDate(int: Int): LocalDate {
        return LocalDate.fromEpochDays(int)
    }

    @TypeConverter
    fun localDateTimeToString(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }

    @TypeConverter
    fun stringToLocalDateTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }

    @TypeConverter
    fun dayOfWeekListToString(days: List<DayOfWeek>): String {
        return days.joinToString(",") { it.name }
    }

    @TypeConverter
    fun stringToDayOfWeekList(string: String): List<DayOfWeek> {
        return if (string.isEmpty()) emptyList() else string.split(",").map { DayOfWeek.valueOf(it) }
    }

    @TypeConverter
    fun uriToString(uri: Uri) = uri.toString()

    @TypeConverter
    fun stringToUri(string: String): Uri = Uri.parse(string)
}