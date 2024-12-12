package com.shub39.reflect.reflect.presentation.reflect_page

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.presentation.reflect_page.component.AnalyticsSheet
import com.shub39.reflect.reflect.presentation.reflect_page.component.PageImage
import com.shub39.reflect.reflect.presentation.reflect_page.component.ReflectEditDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReflectPage(
    state: ReflectPageState,
    action: (ReflectPageAction) -> Unit,
) {
    val context = LocalContext.current
    val listState = rememberLazyGridState()
    val derivedState = remember { derivedStateOf { listState.firstVisibleItemIndex } }

    var editDialog by remember { mutableStateOf(false) }
    var analyticsSheet by remember { mutableStateOf(false) }
    var imageAddDate by remember { mutableStateOf<LocalDate?>(null) }

    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null && state.reflect != null) {
            Log.d("ReflectPage", "Selected uri: $uri")

            if (copyImage(uri, state.reflect.id, imageAddDate ?: LocalDate.now(), context)) {
                action(
                    ReflectPageAction.OnEdit(
                        reflect = state.reflect.copy(
                            lastUpdated = imageAddDate ?: LocalDate.now()
                        )
                    )
                )
            }

            imageAddDate = null
        } else {
            Log.d("ReflectPage", "No media selected")
            imageAddDate = null
        }
    }

    LaunchedEffect(state) {
        if (state.outputPath != null) {
            Toast.makeText(context, "Saved in ${state.outputPath}", Toast.LENGTH_LONG).show()
        }
        if (state.error != null) {
            Toast.makeText(context, context.getString(state.error), Toast.LENGTH_LONG).show()
        }
    }

    if (state.reflect == null) {

        // impossible case
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                strokeCap = StrokeCap.Round
            )
        }

    } else {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = state.reflect.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = state.reflect.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            // Information Chips
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text(text = state.reflect.start.format(DateTimeFormatter.ofPattern("dd/MM/yy"))) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.round_flag_24),
                            contentDescription = null
                        )
                    }
                )

                if (state.reflect.reminder != null) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = state.reflect.reminder.format(
                                    DateTimeFormatter.ofPattern(
                                        "hh:mm a"
                                    )
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.round_access_alarm_24),
                                contentDescription = null
                            )
                        }
                    )
                }

                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = state.reflect.lastUpdated.format(
                                DateTimeFormatter.ofPattern("dd/MM/yy")
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.round_update_24),
                            contentDescription = null
                        )
                    }
                )
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (state.filePaths.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_pictures),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                LazyVerticalGrid(
                    state = listState,
                    columns = GridCells.Adaptive(150.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(state.filePaths.entries.toList()) {
                        PageImage(it, 200)
                    }
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    AnimatedVisibility(
                        visible = derivedState.value == 0,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    pickMedia.launch(
                                        PickVisualMediaRequest(
                                            PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.round_add_24),
                                    contentDescription = null
                                )
                            }

                            FloatingActionButton(
                                onClick = { editDialog = true }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.round_edit_24),
                                    contentDescription = null
                                )
                            }

                            FloatingActionButton(
                                onClick = {
                                    if (!state.isGenerating) {
                                        action(ReflectPageAction.OnPlay)
                                    }

                                    Toast.makeText(
                                        context,
                                        "Not implemented yet...",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                            ) {
                                if (!state.isGenerating) {
                                    Icon(
                                        painter = painterResource(R.drawable.round_play_arrow_24),
                                        contentDescription = null
                                    )
                                } else {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            FloatingActionButton(
                                onClick = { analyticsSheet = true }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.round_analytics_24),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (editDialog && state.reflect != null) {
        ReflectEditDialog(
            state = state,
            onDismiss = { editDialog = false },
            onEdit = { title, description, time ->
                action(
                    ReflectPageAction.OnEdit(
                        Reflect(
                            id = state.reflect.id,
                            title = title,
                            description = description,
                            reminder = time,
                            start = state.reflect.start,
                            lastUpdated = state.reflect.lastUpdated
                        )
                    )
                )
            }
        )
    }

    if (analyticsSheet) {
        AnalyticsSheet(
            dates = state.filePaths.keys.toList(),
            onDismiss = { analyticsSheet = false },
            onPick = {
                imageAddDate = it
                pickMedia.launch(
                    PickVisualMediaRequest(
                        PickVisualMedia.ImageOnly
                    )
                )
                analyticsSheet = false
            }
        )
    }
}