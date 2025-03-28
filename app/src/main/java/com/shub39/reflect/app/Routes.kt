package com.shub39.reflect.app

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object ReflectGraph: Routes
    @Serializable
    data object ReflectList: Routes
    @Serializable
    data object ReflectPage: Routes
    @Serializable
    data object PermissionPage: Routes
    @Serializable
    data object ReflectVideo: Routes

    @Serializable
    data object SettingsGraph: Routes
    @Serializable
    data object SettingsMain: Routes
    @Serializable
    data object LookAndFeel: Routes
    @Serializable
    data object About: Routes
}