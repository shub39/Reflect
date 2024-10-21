package com.shub39.reflect.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReflectDao {

    @Query("SELECT * FROM reflect")
    suspend fun getAllReflects(): List<Reflect>

    @Query("SELECT * FROM reflect WHERE id = :id")
    suspend fun getReflectById(id: Long): Reflect?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReflect(reflect: Reflect)

    @Delete
    suspend fun deleteReflect(reflect: Reflect)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReflect(reflect: Reflect)

}