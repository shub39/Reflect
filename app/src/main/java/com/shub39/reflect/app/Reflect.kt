package com.shub39.reflect.app

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shub39.reflect.core.presentation.theme.ReflectTheme
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectList
import com.shub39.reflect.reflect.presentation.settings.LookAndFeelPage
import com.shub39.reflect.reflect.presentation.settings.SettingsMainPage
import com.shub39.reflect.reflect.presentation.viewmodels.ReflectListViewModel
import com.shub39.reflect.reflect.presentation.viewmodels.ReflectPageViewModel
import com.shub39.reflect.reflect.presentation.viewmodels.SettingsPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Reflect(
    listVM: ReflectListViewModel = koinViewModel(),
    pageVM: ReflectPageViewModel = koinViewModel(),
    settingsVM: SettingsPageViewModel = koinViewModel()
) {
    val navController = rememberNavController()

    val settingsState by settingsVM.state.collectAsStateWithLifecycle()

    ReflectTheme(
        theme = settingsState.theme
    ) {
        NavHost(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            navController = navController,
            startDestination = Routes.ReflectGraph
        ) {
            navigation<Routes.ReflectGraph>(
                startDestination = Routes.ReflectList
            ) {
                composable<Routes.ReflectPage> {

                }

                composable<Routes.ReflectList> {
                    val state by listVM.state.collectAsStateWithLifecycle()

                    ReflectList(
                        state = state,
                        action = listVM::onAction,
                        onNavigateToPage = { navController.navigate(Routes.ReflectPage) },
                        onNavigateToSettings = { navController.navigate(Routes.SettingsGraph) }
                    )
                }

                composable<Routes.ReflectVideo> {

                }

                composable<Routes.PermissionPage> {

                }
            }

            navigation<Routes.SettingsGraph>(
                startDestination = Routes.SettingsMainPage
            ) {
                composable<Routes.SettingsMainPage> {
                    SettingsMainPage(
                        onNavLookAndFeel = { navController.navigate(Routes.LookAndFeel) }
                    )
                }

                composable<Routes.LookAndFeel> {
                    LookAndFeelPage(
                        state = settingsState,
                        action = settingsVM::onAction
                    )
                }
            }
        }
    }
}