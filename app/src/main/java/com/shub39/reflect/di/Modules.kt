package com.shub39.reflect.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.shub39.reflect.core.data.DatabaseFactory
import com.shub39.reflect.core.data.DatastoreFactory
import com.shub39.reflect.reflect.data.database.ReflectDatabase
import com.shub39.reflect.reflect.presentation.ReflectVM
import com.shub39.reflect.reflect.data.repository.ReflectRepository
import com.shub39.reflect.core.data.PrefDatastoreImpl
import com.shub39.reflect.core.domain.PrefDatastore
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

    // database, datastore and repository
    singleOf(::DatabaseFactory)
    single {
        get<DatabaseFactory>()
            .create()
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<ReflectDatabase>().reflectDao() }
    single { get<ReflectDatabase>().reflectImageDao() }

    singleOf(::DatastoreFactory)
    single { get<DatastoreFactory>().getPreferencesDataStore() }
    singleOf(::PrefDatastoreImpl).bind<PrefDatastore>()

    singleOf(::ReflectRepository).bind<ReflectRepo>()

    //video stuff
    singleOf(::FFmpegHandler).bind<Video>()

    // viewmodel
    viewModelOf(::ReflectVM)
}