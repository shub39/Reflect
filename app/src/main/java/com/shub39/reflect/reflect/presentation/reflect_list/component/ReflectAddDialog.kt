package com.shub39.reflect.reflect.presentation.reflect_list.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.shub39.reflect.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shub39.reflect.reflect.domain.Reflect
import com.shub39.reflect.reflect.presentation.isValidTitle
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectAddDialog(
    onAdd: (Reflect) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var addReminder by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.add_reflect),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    singleLine = true,
                    label = { Text(stringResource(R.string.title)) }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    shape = MaterialTheme.shapes.large,
                    singleLine = true,
                    label = { Text(stringResource(R.string.description)) }
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.reminder),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(Modifier.padding(16.dp))

                    Switch(
                        checked = addReminder,
                        onCheckedChange = { addReminder = it }
                    )
                }

                AnimatedVisibility(
                    visible = addReminder
                ) {
                    TimeInput(
                        state = timePickerState,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                Button(
                    onClick = {
                        onAdd(
                            Reflect(
                                title = title,
                                description = description,
                                start = LocalDate.now(),
                                reminder = if (addReminder) LocalTime.of(
                                    timePickerState.hour,
                                    timePickerState.minute
                                ) else null
                            )
                        )

                        onDismiss()
                    },
                    shape = MaterialTheme.shapes.extraLarge,
                    enabled = isValidTitle(title) && description.isNotBlank() && description.length < 50
                ) {
                    Text(
                        text = stringResource(R.string.add)
                    )
                }
            }
        }
    }
}