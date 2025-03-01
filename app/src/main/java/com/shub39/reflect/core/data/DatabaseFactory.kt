package com.shub39.reflect.core.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shub39.reflect.reflect.data.database.ReflectDatabase

class DatabaseFactory (
    private val context: Context
) {
    fun create(): RoomDatabase.Builder<ReflectDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(ReflectDatabase.DB_NAME)

        return Room.databaseBuilder(appContext, dbFile.absolutePath)
    }
}