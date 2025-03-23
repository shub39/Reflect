package com.shub39.reflect.reflect.data.repository

import com.shub39.reflect.reflect.data.database.ReflectDao
import com.shub39.reflect.reflect.data.database.ReflectImageDao
import com.shub39.reflect.reflect.data.mappers.toReflect
import com.shub39.reflect.reflect.data.mappers.toReflectEntity
import com.shub39.reflect.reflect.data.mappers.toReflectImage
import com.shub39.reflect.reflect.data.mappers.toReflectImageEntity
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.domain.ReflectImage
import com.shub39.reflect.reflect.domain.ReflectRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReflectRepository(
    private val reflectDao: ReflectDao,
    private val imageDao: ReflectImageDao
): ReflectRepo {
    override fun getReflects(): Flow<List<Reflect>> {
        return reflectDao.getReflects().map { entities ->
            entities.map { it.toReflect() }
        }
    }

    override suspend fun upsertReflect(reflect: Reflect) {
        reflectDao.upsertReflect(reflect.toReflectEntity())
    }

    override suspend fun deleteReflect(reflect: Reflect) {
        reflectDao.deleteReflect(reflect.toReflectEntity())
    }

    override suspend fun getReflect(id: Long): Reflect {
        return reflectDao.getReflectById(id).toReflect()
    }

    override fun getReflectImages(id: Long): Flow<List<ReflectImage>> {
        return imageDao.getImagesByRefId(id).map { entities ->
            entities.map { it.toReflectImage() }
        }
    }

    override suspend fun upsertReflectImage(reflectImage: ReflectImage) {
        imageDao.insertImage(reflectImage.toReflectImageEntity())
    }

    override suspend fun deleteReflectImage(reflectImage: ReflectImage) {
        imageDao.deleteImage(reflectImage.toReflectImageEntity())
    }
}