package com.shub39.reflect.reflect.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ReflectImageDao {

    @Query("SELECT * FROM images WHERE refId = :id")
    fun getImagesByRefId(id: Long): Flow<List<ReflectImageEntity>>

    @Upsert
    suspend fun insertImage(imageEntity: ReflectImageEntity)

    @Delete
    suspend fun deleteImage(imageEntity: ReflectImageEntity)

}