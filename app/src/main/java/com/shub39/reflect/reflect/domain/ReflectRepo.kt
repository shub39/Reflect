package com.shub39.reflect.reflect.domain

import kotlinx.coroutines.flow.Flow

interface ReflectRepo {
    fun getReflects(): Flow<List<Reflect>>
    suspend fun upsertReflect(reflect: Reflect)
    suspend fun deleteReflect(reflect: Reflect)
    suspend fun getReflect(id: Long): Reflect
}