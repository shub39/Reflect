package com.shub39.reflect.reflect.presentation.reflect_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.core.presentation.PageFill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectList(
    state: ReflectListState,
    action: (ReflectListAction) -> Unit,
    onNavigateToPage: () -> Unit,
    onNavigateToSettings: () -> Unit
) = PageFill {

    Scaffold(
        modifier = Modifier
            .widthIn(max = 700.dp)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
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
                }
            )
        }
    ) { padding ->

    }
}