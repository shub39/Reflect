package com.shub39.reflect.reflect.presentation.viewmodels

import com.shub39.reflect.reflect.presentation.reflect_list.ReflectListState
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPageState
import com.shub39.reflect.reflect.presentation.settings.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow

class StateLayer {
    val listState = MutableStateFlow(ReflectListState())
    val pageState = MutableStateFlow(ReflectPageState())
    val settingsState = MutableStateFlow(SettingsState())
}