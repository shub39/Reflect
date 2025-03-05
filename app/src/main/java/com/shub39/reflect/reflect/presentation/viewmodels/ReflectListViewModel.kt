package com.shub39.reflect.reflect.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.reflect.domain.ReflectRepo
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectListAction
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ReflectListViewModel(
    private val states: StateLayer,
    private val repo: ReflectRepo
) : ViewModel() {

    private var savedJob: Job? = null

    val state = states.listState.asStateFlow()
        .onStart { getReflects() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ReflectListState()
        )

    fun onAction(action: ReflectListAction) {
        when (action) {
            is ReflectListAction.OnAddReflect -> {
            }

            is ReflectListAction.OnChangeReflect -> {
            }
        }
    }

    private fun getReflects() {
        savedJob?.cancel()
        savedJob = repo
            .getReflects()
            .onEach { reflects ->
                states.listState.update { state ->
                    state.copy(
                        reflects = reflects
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}