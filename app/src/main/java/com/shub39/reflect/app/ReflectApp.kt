package com.shub39.reflect.app

import android.app.Application
import com.shub39.reflect.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ReflectApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ReflectApp)
            modules(modules)
        }

    }

}