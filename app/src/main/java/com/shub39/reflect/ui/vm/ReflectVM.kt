package com.shub39.reflect.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.data.Reflect
import com.shub39.reflect.data.ReflectDatabase
import com.shub39.reflect.ui.page.reflect_list.HomePageState
import com.shub39.reflect.ui.page.reflect_list.toReflectUi
import com.shub39.reflect.ui.page.reflect_page.ReflectPageAction
import com.shub39.reflect.ui.page.reflect_page.ReflectPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class ReflectVM(
    application: Application
): AndroidViewModel(application) {

    private val db = ReflectDatabase.getDatabase(application)
    private val dao = db.reflectDao()

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
        when (action) {
            is ReflectPageAction.OnDelete -> {}
            is ReflectPageAction.OnEdit -> {}
            is ReflectPageAction.OnPlay -> {}
            is ReflectPageAction.OnAdd -> {}
        }
    }

    fun addReflect(
        title: String,
        description: String,
        reminder: LocalTime? = null
    ) {
        viewModelScope.launch {
            dao.insertReflect(
                Reflect(
                    title = title,
                    description = description,
                    reminder = reminder,
                    start = LocalDate.now()
                )
            )

            getReflects()
        }
    }

    fun changeReflect(id: Long) {
        viewModelScope.launch {
            _reflectState.update {
                it.copy(
                    reflect = dao.getReflectById(id)
                )
            }
        }
    }

    private fun getReflects() {
        viewModelScope.launch {
            _homeState.update {
                it.copy(
                    reflects = dao.getAllReflects().map { it.toReflectUi() }.sortedBy { it.lastUpdated }
                )
            }
        }
    }

    private fun updateReflect(reflect: Reflect) {
        viewModelScope.launch {
            dao.updateReflect(reflect)

            getReflects()
        }
    }

    private fun deleteReflect(reflect: Reflect) {
        viewModelScope.launch {
            dao.deleteReflect(reflect)

            getReflects()
        }
    }

}