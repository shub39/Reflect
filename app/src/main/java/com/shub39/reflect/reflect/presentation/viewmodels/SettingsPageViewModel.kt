package com.shub39.reflect.reflect.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.reflect.core.domain.PrefDatastore
import com.shub39.reflect.reflect.presentation.settings.SettingsPageAction
import com.shub39.reflect.reflect.presentation.settings.SettingsState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsPageViewModel(
    private val states: StateLayer,
    private val datastore: PrefDatastore
): ViewModel() {

    private var themeObserveJob: Job? = null

    val state = states.settingsState.asStateFlow()
        .onStart { observeThemes() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SettingsState()
        )

    fun onAction(action: SettingsPageAction) = viewModelScope.launch {
        when (action) {
            is SettingsPageAction.OnUpdateFont -> datastore.setFont(action.font)
            is SettingsPageAction.OnUpdateMaterialPref -> datastore.setMaterialTheme(action.pref)
            is SettingsPageAction.OnUpdateSeedColor -> datastore.setSeedColor(action.color)
            is SettingsPageAction.OnUpdateStyle -> datastore.setStyle(action.style)
            is SettingsPageAction.OnUpdateAppTheme -> datastore.setAppTheme(action.appTheme)
            is SettingsPageAction.OnUpdateAmoledPref -> datastore.setAmoled(action.pref)
        }
    }

    private fun observeThemes() = viewModelScope.launch {
        themeObserveJob?.cancel()
        themeObserveJob = launch {
            datastore.getAppTheme()
                .onEach { theme ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                appThemePref = theme
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)

            datastore.getFont()
                .onEach { font ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                font = font
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)

            datastore.getSeedColor()
                .onEach { color ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                seedColor = color
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)

            datastore.getMaterialThemePref()
                .onEach { pref ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                materialTheme = pref
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)

            datastore.getStyle()
                .onEach { style ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                style = style
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)

            datastore.getAmoledPref()
                .onEach { amoled ->
                    states.settingsState.update {
                        it.copy(
                            theme = it.theme.copy(
                                withAmoled = amoled
                            )
                        )
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}