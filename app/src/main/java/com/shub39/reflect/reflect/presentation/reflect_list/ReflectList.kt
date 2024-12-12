package com.shub39.reflect.reflect.presentation.reflect_list

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.core.presentation.theme.ReflectTheme
import com.shub39.reflect.reflect.presentation.reflect_list.component.PreviewImage
import com.shub39.reflect.reflect.presentation.reflect_list.component.ReflectAddDialog
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectList(
    state: ReflectListState,
    action: (ReflectListAction) -> Unit,
    onNavigate: (id: Long) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showAddDialog = true }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.round_add_24),
                            contentDescription = null
                        )
                    }
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.reflects, key = { it.id }) {

                    ListItem(
                        headlineContent = {
                            Text(text = it.title)
                        },
                        supportingContent = {
                            Text(text = it.description)
                        },
                        trailingContent = {
                            IconButton(
                                onClick = {
                                    onNavigate(it.id)
                                    action(ReflectListAction.OnChangeReflect(it.id))
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.round_arrow_forward_24),
                                    contentDescription = null
                                )
                            }
                        },
                        overlineContent = {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                AssistChip(
                                    onClick = {},
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.round_flag_24),
                                            contentDescription = null
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = it.start.format(DateTimeFormatter.ofPattern("dd/MM/yy"))
                                        )
                                    }
                                )

                                if (it.reminder != null) {
                                    AssistChip(
                                        onClick = {},
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(R.drawable.round_access_alarm_24),
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = it.reminder.format(
                                                    DateTimeFormatter.ofPattern(
                                                        "hh:mm a"
                                                    )
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(it.preview.entries.toList()) { data ->
                            PreviewImage(data)
                        }
                    }

                    HorizontalDivider()
                }
            }
        }
    }

    if (showAddDialog) {
        ReflectAddDialog(
            onAdd = { action(ReflectListAction.OnAddReflect(it)) },
            onDismiss = { showAddDialog = false }
        )
    }

}

@Preview(
    showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF,
    device = "spec:width=673dp,height=841dp",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
private fun ReflectListPreview() {
    ReflectTheme {
        Scaffold { pa ->
            Box(Modifier.padding(pa)) {
                ReflectList(
                    state = ReflectListState(
                        reflects = (0L..100L).map { ref ->
                            ReflectUI(
                                id = ref,
                                title = "Reflect no: $ref",
                                description = "description number $ref",
                                start = LocalDate.now(),
                                reminder = if (ref % 2 == 0L) LocalTime.now() else null,
                                preview = (0L..10L).associate {
                                    LocalDate.now().minusDays(it) to ""
                                }
                            )
                        }
                    ),
                    onNavigate = {},
                    action = {}
                )
            }
        }
    }
}