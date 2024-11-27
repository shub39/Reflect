package com.shub39.reflect.reflect.data.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

object TimeConverters {

    @TypeConverter
    fun longToLocalTime(value: Long?): LocalTime? {
        return value?.let { LocalTime.ofSecondOfDay(it) }
    }

    @TypeConverter
    fun localTimeToLong(time: LocalTime?): Long? {
        return time?.toSecondOfDay()?.toLong()
    }

    @TypeConverter
    fun longToLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun localDateToLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

}