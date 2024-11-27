package com.shub39.reflect.reflect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.domain.ReflectRepo
import com.shub39.reflect.reflect.presentation.reflect_list.HomePageState
import com.shub39.reflect.reflect.presentation.reflect_list.getFilePaths
import com.shub39.reflect.reflect.presentation.reflect_list.toReflect
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
import java.time.LocalDate
import java.time.LocalTime

class ReflectVM(
    private val repo: ReflectRepo,
) : ViewModel() {

    private var savedJob: Job? = null

    private val _homeState = MutableStateFlow(HomePageState())
    private val _reflectState = MutableStateFlow(ReflectPageState())

    val homeState = _homeState.asStateFlow()
        .onStart { getReflects() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomePageState()
        )

    val reflectState = _reflectState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ReflectPageState()
        )

    fun onReflectAction(action: ReflectPageAction) {
        viewModelScope.launch {
            when (action) {
                is ReflectPageAction.OnDelete -> {

                }

                is ReflectPageAction.OnEdit -> {
                    updateReflect(action.reflect)
                    changeReflect(action.reflect.id)
                }

                is ReflectPageAction.OnPlay -> {}
                is ReflectPageAction.OnAdd -> {}
            }
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

    fun addReflect(
        title: String,
        description: String,
        reminder: LocalTime? = null
    ) {
        viewModelScope.launch {
            repo.upsertReflect(
                Reflect(
                    title = title,
                    description = description,
                    reminder = reminder,
                    start = LocalDate.now()
                )
            )
        }
    }

    fun changeReflect(id: Long) {
        viewModelScope.launch {
            val ref = _homeState.value.reflects.find { it.id == id }?.toReflect()

            if (ref != null) {
                _reflectState.update {
                    it.copy(
                        reflect = ref,
                        filePaths = getFilePaths(ref.id.toString(), 0)
                    )
                }
            }
        }
    }

    private suspend fun updateReflect(reflect: Reflect) {
        repo.upsertReflect(reflect)
    }

    private suspend fun deleteReflect(reflect: Reflect) {
        repo.deleteReflect(reflect)
    }

}