package com.shub39.reflect.ui.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shub39.reflect.ui.page.reflect_list.ReflectList
import com.shub39.reflect.ui.vm.ReflectVM
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import com.shub39.reflect.R
import com.shub39.reflect.ui.page.component.ReflectAddDialog
import com.shub39.reflect.ui.page.reflect_page.ReflectPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reflect(
    vm: ReflectVM = koinViewModel()
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val homeState by vm.homeState.collectAsStateWithLifecycle()
    val reflectPageState by vm.reflectState.collectAsStateWithLifecycle()

    var addReflectDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = currentDestination?.route == HomePage::class.qualifiedName,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                LargeTopAppBar(
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }

            AnimatedVisibility(
                visible = currentDestination?.route != HomePage::class.qualifiedName,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentDestination?.route == HomePage::class.qualifiedName,
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
            startDestination = HomePage
        ) {
            composable<HomePage> {
                ReflectList(
                    state = homeState,
                    onNavigate = {
                        vm.changeReflect(it)
                        navController.navigate(ReflectPage)
                    }
                )
            }

            composable<ReflectPage> {
                ReflectPage(
                    state = reflectPageState,
                    action = vm::onReflectAction
                )
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

@Serializable
object HomePage

@Serializable
object ReflectPage