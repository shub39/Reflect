package com.shub39.reflect.di

import com.shub39.reflect.reflect.data.database.ReflectDatabase
import com.shub39.reflect.reflect.presentation.ReflectVM
import com.shub39.reflect.reflect.data.repository.ReflectRepository
import com.shub39.reflect.reflect.domain.ReflectRepo
import com.shub39.reflect.reflect.domain.Video
import com.shub39.reflect.reflect.data.video.FFmpegHandler
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val modules = module {
    // imageLoader
    single { provideImageLoader(get()) }

    // database
    single { ReflectDatabase.getDatabase(get()) }
    single { get<ReflectDatabase>().reflectDao() }
    singleOf(::ReflectRepository).bind<ReflectRepo>()

    //video stuff
    singleOf(::FFmpegHandler).bind<Video>()

    // viewmodel
    viewModelOf(::ReflectVM)
}