package com.shub39.reflect.app

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object ReflectGraph: Routes

    @Serializable
    data object ReflectList: Routes

    @Serializable
    data object ReflectPage: Routes
}