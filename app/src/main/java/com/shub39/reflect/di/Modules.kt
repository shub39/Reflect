package com.shub39.reflect.di

import com.shub39.reflect.reflect.data.database.ReflectDatabase
import com.shub39.reflect.reflect.presentation.ReflectVM
import com.shub39.reflect.reflect.data.repository.ReflectRepository
import com.shub39.reflect.reflect.domain.ReflectRepo
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val modules = module {
    // imageLoader
    single { provideImageLoader(get()) }

    // database
    single { ReflectDatabase.getDatabase(get()) }
    single { get<ReflectDatabase>().reflectDao() }
    singleOf(::ReflectRepository).bind<ReflectRepo>()

    // viewmodel
    viewModelOf(::ReflectVM)
}