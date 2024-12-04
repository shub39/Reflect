package com.shub39.reflect.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shub39.reflect.reflect.presentation.reflect_list.ReflectList
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.navigation
import org.koin.androidx.compose.koinViewModel
import com.shub39.reflect.R
import com.shub39.reflect.reflect.presentation.ReflectVM
import com.shub39.reflect.reflect.presentation.component.ReflectAddDialog
import com.shub39.reflect.reflect.presentation.reflect_page.ReflectPage

@Composable
fun Reflect(
    vm: ReflectVM = koinViewModel()
) {
    val navController = rememberNavController()

    val routes = listOf(
        Routes.ReflectList,
        Routes.ReflectPage
    )

    val homeState by vm.homeState.collectAsStateWithLifecycle()
    val reflectPageState by vm.reflectState.collectAsStateWithLifecycle()

    var addReflectDialog by remember { mutableStateOf(false) }
    var currentRoute by remember { mutableStateOf(routes[0]) }

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentRoute == Routes.ReflectList,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    onClick = { addReflectDialog = true },
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Icon(
                        painter = painterResource(R.drawable.round_add_24),
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Routes.ReflectGraph
        ) {
            navigation<Routes.ReflectGraph>(
                startDestination = Routes.ReflectList
            ) {
                composable<Routes.ReflectList> {
                    currentRoute = routes[0]

                    ReflectList(
                        state = homeState,
                        onNavigate = {
                            navController.navigate(Routes.ReflectPage)
                            vm.changeReflect(it)
                        },
                    )
                }

                composable<Routes.ReflectPage> {
                    currentRoute = routes[1]

                    ReflectPage(
                        state = reflectPageState,
                        action = vm::onReflectAction,
                    )
                }
            }
        }

    }

    if (addReflectDialog) {
        ReflectAddDialog(
            onAdd = vm::addReflect,
            onDismiss = { addReflectDialog = false }
        )
    }

}