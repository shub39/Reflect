package com.shub39.reflect.reflect.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ReflectDao {

    @Query("SELECT * FROM reflect")
    suspend fun getAllReflects(): List<ReflectEntity>

    @Query("SELECT * FROM reflect")
    fun getReflects(): Flow<List<ReflectEntity>>

    @Upsert
     suspend fun upsertReflect(reflect: ReflectEntity)

    @Query("SELECT * FROM reflect WHERE id = :id")
    suspend fun getReflectById(id: Long): ReflectEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReflect(reflect: ReflectEntity)

    @Delete
    suspend fun deleteReflect(reflect: ReflectEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReflect(reflect: ReflectEntity)

}