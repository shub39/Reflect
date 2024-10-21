package com.shub39.reflect.di

import com.shub39.reflect.ui.vm.ReflectVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val modules = module {
    single { provideImageLoader(get()) }
    viewModel { ReflectVM(get()) }
}