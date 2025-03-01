package com.shub39.reflect.reflect.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReflectEntity::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverters::class)
abstract class ReflectDatabase: RoomDatabase() {

    abstract fun reflectDao(): ReflectDao

    companion object {
        const val DB_NAME = "reflect_database"
    }

}