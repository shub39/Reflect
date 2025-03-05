package com.shub39.reflect.reflect.presentation.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.core.presentation.PageFill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsMainPage(
    state: SettingsState,
    action: (SettingsPageAction) -> Unit
) = PageFill {
    var appThemeSelect by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .widthIn(max = 700.dp)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.settings)) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                ListItem(
                    headlineContent = {
                        Text(text = stringResource(R.string.app_theme))
                    },
                    trailingContent = {
                        IconButton(
                            onClick = { appThemeSelect = true }
                        ) { }
                    }
                )
            }
        }
    }

    if (appThemeSelect) {

    }
}