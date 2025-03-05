package com.shub39.reflect.reflect.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPageAction
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPageState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReflectPageViewModel(
    private val states: StateLayer
): ViewModel() {

    val state = states.pageState.asStateFlow()
        .onStart {  }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ReflectPageState()
        )

    fun onAction(action: ReflectPageAction) = viewModelScope.launch {
        when (action) {
            is ReflectPageAction.OnDelete -> {}
            is ReflectPageAction.OnEdit -> {}
            ReflectPageAction.OnPlay -> {}
        }
    }

}