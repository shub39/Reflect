package com.shub39.reflect.reflect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.domain.ReflectRepo
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectListAction
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectListState
import com.shub39.reflect.reflect.presentation.reflect_list.toReflectUi
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPageAction
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPageState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReflectVM(
    private val repo: ReflectRepo,
//    private val video: Video,
) : ViewModel() {

    private var savedJob: Job? = null

    private val _homeState = MutableStateFlow(ReflectListState())
    private val _reflectState = MutableStateFlow(ReflectPageState())

    val homeState = _homeState.asStateFlow()
        .onStart { getReflects() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ReflectListState()
        )

    val reflectState = _reflectState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ReflectPageState()
        )

    fun onReflectListAction(action: ReflectListAction) {
        when (action) {
            is ReflectListAction.OnAddReflect -> {
                addReflect(action.reflect)
            }

            is ReflectListAction.OnChangeReflect -> {
                changeReflect(action.reflectId)
            }
        }
    }

    fun onReflectAction(action: ReflectPageAction) {
        when (action) {
            is ReflectPageAction.OnDelete -> {

            }

            is ReflectPageAction.OnEdit -> {
                updateReflect(action.reflect)
            }

            is ReflectPageAction.OnPlay -> {
                // generateVideo()
            }
        }
    }

    fun changeReflect(id: Long) {
        viewModelScope.launch {
            _reflectState.update {
                it.copy(
                    reflect = repo.getReflect(id),
                    filePaths = getFilePathsWithDates(id.toString(), 0)
                )
            }
        }
    }

    private fun addReflect(
        reflect: Reflect
    ) {
        viewModelScope.launch {
            repo.upsertReflect(reflect)
        }
    }

    private fun getReflects() {
        savedJob?.cancel()
        savedJob = repo
            .getReflects()
            .onEach { reflects ->
                _homeState.update { state ->
                    state.copy(
                        reflects = reflects.map { it.toReflectUi() }
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun updateReflect(reflect: Reflect) {
        viewModelScope.launch {
            repo.upsertReflect(reflect)

            _reflectState.update {
                it.copy(
                    reflect = reflect,
                    filePaths = getFilePathsWithDates(reflect.id.toString(), 0)
                )
            }
        }
    }

    private fun deleteReflect(reflect: Reflect) {
        viewModelScope.launch {
            repo.deleteReflect(reflect)
        }
    }

}