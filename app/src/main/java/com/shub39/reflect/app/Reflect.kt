package com.shub39.reflect.app

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectList
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.navigation
import com.shub39.reflect.onboarding.PermissionPage
import org.koin.androidx.compose.koinViewModel
import com.shub39.reflect.reflect.presentation.ReflectVM
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPage

@Composable
fun Reflect(
    vm: ReflectVM = koinViewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val homeState by vm.homeState.collectAsStateWithLifecycle()
    val reflectPageState by vm.reflectState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED
        ) { navController.navigate(Routes.PermissionPage) }
    }

    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Routes.ReflectGraph
        ) {
            navigation<Routes.ReflectGraph>(
                startDestination = Routes.ReflectList
            ) {
                composable<Routes.ReflectList> {
                    ReflectList(
                        state = homeState,
                        onNavigate = {
                            navController.navigate(Routes.ReflectPage)
                        },
                        action = vm::onReflectListAction
                    )
                }

                composable<Routes.ReflectPage> {
                    ReflectPage(
                        state = reflectPageState,
                        action = vm::onReflectAction,
                    )
                }

                composable<Routes.RefelectVideo> {
                    TODO()
                }
            }

            navigation<Routes.SettingsGraph>(
                startDestination = Routes.SettingsMainPage
            ) {
                composable<Routes.SettingsMainPage> {
                    TODO()
                }
            }

            composable<Routes.PermissionPage> {
                PermissionPage()
            }
        }

    }
}