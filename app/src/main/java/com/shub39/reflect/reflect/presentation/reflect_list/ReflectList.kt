package com.shub39.reflect.reflect.presentation.reflect_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.core.domain.AppTheme
import com.shub39.reflect.core.domain.Theme
import com.shub39.reflect.core.presentation.PageFill
import com.shub39.reflect.core.presentation.theme.ReflectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectList(
    state: ReflectListState,
    action: (ReflectListAction) -> Unit,
    onNavigateToPage: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToSettings: () -> Unit
) = PageFill {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .widthIn(max = 700.dp)
            .fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                actions = {
                    IconButton(
                        onClick = onNavigateToSettings
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }

                    IconButton(
                        onClick = { showAddDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add New"
                        )
                    }
                }
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun Preview() {
    ReflectTheme(
        theme = Theme(
            appThemePref = AppTheme.DARK
        )
    ) {
        ReflectList(
            state = ReflectListState(),
            action = {},
            onNavigateToPage = {},
            onNavigateToAdd = {},
            onNavigateToSettings = {}
        )
    }
}