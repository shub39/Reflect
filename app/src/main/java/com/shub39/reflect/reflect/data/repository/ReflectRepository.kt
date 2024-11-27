package com.shub39.reflect.reflect.data.repository

import com.shub39.reflect.reflect.data.database.ReflectDao
import com.shub39.reflect.reflect.data.mappers.toReflect
import com.shub39.reflect.reflect.data.mappers.toReflectEntity
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.domain.ReflectRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReflectRepository(
    private val dao: ReflectDao
): ReflectRepo {
    override fun getReflects(): Flow<List<Reflect>> {
        return dao.getReflects().map { entities ->
            entities.map { it.toReflect() }
        }
    }

    override suspend fun upsertReflect(reflect: Reflect) {
        dao.upsertReflect(reflect.toReflectEntity())
    }

    override suspend fun deleteReflect(reflect: Reflect) {
        dao.deleteReflect(reflect.toReflectEntity())
    }
}