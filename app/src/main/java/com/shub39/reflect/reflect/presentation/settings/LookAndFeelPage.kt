package com.shub39.reflect.reflect.presentation.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materialkolor.PaletteStyle
import com.materialkolor.ktx.from
import com.materialkolor.palettes.TonalPalette
import com.materialkolor.rememberDynamicColorScheme
import com.shub39.reflect.R
import com.shub39.reflect.core.domain.AppTheme
import com.shub39.reflect.core.domain.Theme
import com.shub39.reflect.core.presentation.ColorPickerDialog
import com.shub39.reflect.core.presentation.DialogBox
import com.shub39.reflect.core.presentation.PageFill
import com.shub39.reflect.core.presentation.caps
import com.shub39.reflect.core.presentation.theme.ReflectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookAndFeelPage(
    state: SettingsState,
    action: (SettingsPageAction) -> Unit
) = PageFill {
    var appThemePicker by remember { mutableStateOf(false) }
    var seedColorPicker by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.widthIn(max = 700.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.look_and_feel)
                    )
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                ListItem(
                    headlineContent = {
                        Text(text = stringResource(R.string.app_theme))
                    },
                    supportingContent = {
                        Text(text = state.theme.appThemePref.name.caps())
                    },
                    trailingContent = {
                        IconButton(
                            onClick = { appThemePicker = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "Open Picker"
                            )
                        }
                    }
                )
            }

            item {
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.amoled)
                        )
                    },
                    supportingContent = {
                        Text(
                            text = stringResource(R.string.amoled_desc)
                        )
                    },
                    trailingContent = {
                        Switch(
                            checked = state.theme.withAmoled,
                            onCheckedChange = {
                                action(SettingsPageAction.OnUpdateAmoledPref(it))
                            }
                        )
                    }
                )
            }

            item {
                ListItem(
                    headlineContent =  {
                        Text(
                            text = stringResource(R.string.material_you)
                        )
                    },
                    supportingContent = {
                        Text(
                            text = stringResource(R.string.material_you_desc)
                        )
                    },
                    trailingContent =  {
                        Switch(
                            checked = state.theme.materialTheme,
                            onCheckedChange = {
                                action(SettingsPageAction.OnUpdateMaterialPref(it))
                            }
                        )
                    }
                )
            }

            item {
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.seed_color)
                        )
                    },
                    supportingContent = {
                        Text(
                            text = stringResource(R.string.seed_color_desc)
                        )
                    },
                    trailingContent = {
                        IconButton(
                            onClick = { seedColorPicker = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "Pick Color"
                            )
                        }
                    }
                )
            }

            item {
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.palette_style)
                        )
                    },
                    supportingContent = {
                        val scrollState = rememberScrollState()

                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .horizontalScroll(scrollState),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            PaletteStyle.entries.forEach { style ->
                                val scheme = rememberDynamicColorScheme(
                                    primary = if (state.theme.materialTheme) {
                                        colorResource(android.R.color.system_accent1_200)
                                    } else {
                                        state.theme.seedColor
                                    },
                                    isDark = when (state.theme.appThemePref) {
                                        AppTheme.LIGHT -> false
                                        AppTheme.DARK -> true
                                        AppTheme.SYSTEM -> isSystemInDarkTheme()
                                    },
                                    isAmoled = state.theme.withAmoled,
                                    style = style
                                )

                                SelectableMiniPalette(
                                    selected = state.theme.style == style,
                                    onClick = {
                                        action(SettingsPageAction.OnUpdateStyle(style))
                                    },
                                    contentDescription = { style.name },
                                    accents = listOf(
                                        TonalPalette.from(scheme.primary),
                                        TonalPalette.from(scheme.tertiary),
                                        TonalPalette.from(scheme.secondary)
                                    )
                                )
                            }
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.padding(80.dp))
            }
        }

    }

    if (seedColorPicker) {
        ColorPickerDialog(
            initialColor = state.theme.seedColor,
            onSelect = { action(SettingsPageAction.OnUpdateSeedColor(it)) },
            onDismiss = { seedColorPicker = false }
        )
    }

    if (appThemePicker) {
        DialogBox(
            onDismissRequest = { appThemePicker = false }
        ) {
            Column {
                AppTheme.entries.forEach { theme ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state.theme.appThemePref == theme,
                            onClick = {
                                action(SettingsPageAction.OnUpdateAppTheme(theme))
                            }
                        )

                        Text(text = theme.name.caps())
                    }
                }
            }
        }
    }
}

// yeeted from https://github.com/SkyD666/PodAura/blob/master/app/src/main/java/com/skyd/anivu/ui/screen/settings/appearance/AppearanceScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectableMiniPalette(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    contentDescription: () -> String,
    accents: List<TonalPalette>,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.inverseOnSurface,
    ) {
        TooltipBox(
            modifier = modifier,
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip {
                    Text(contentDescription())
                }
            },
            state = rememberTooltipState()
        ) {
            Surface(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(12.dp)
                    .size(50.dp),
                shape = CircleShape,
                color = Color(accents[0].tone(60))
            ) {
                Box {
                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .offset((-25).dp, 25.dp),
                        color = Color(accents[1].tone(85)),
                    ) {}
                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(25.dp, 25.dp),
                        color = Color(accents[2].tone(75)),
                    ) {}
                    val animationSpec = spring<Float>(stiffness = Spring.StiffnessMedium)
                    AnimatedVisibility(
                        visible = selected,
                        enter = scaleIn(animationSpec) + fadeIn(animationSpec),
                        exit = scaleOut(animationSpec) + fadeOut(animationSpec),
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Check,
                                contentDescription = "Checked",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(16.dp),
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ReflectTheme(
        theme = Theme()
    ) {
        LookAndFeelPage(
            state = SettingsState(),
            action = {}
        )
    }
}