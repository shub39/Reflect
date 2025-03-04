package com.shub39.reflect.reflect.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReflectEntity::class, ReflectImageEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ReflectDatabase: RoomDatabase() {

    abstract fun reflectDao(): ReflectDao
    abstract fun reflectImageDao(): ReflectImageDao

    companion object {
        const val DB_NAME = "reflect_database"
    }

}