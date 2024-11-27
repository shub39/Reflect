package com.shub39.reflect.reflect.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReflectEntity::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverters::class)
abstract class ReflectDatabase: RoomDatabase() {

    abstract fun reflectDao(): ReflectDao

    companion object {
        @Volatile
        private var INSTANCE: ReflectDatabase? = null

        fun getDatabase(context: Context): ReflectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReflectDatabase::class.java,
                    "reflect_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}