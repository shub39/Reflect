package com.shub39.reflect.ui.page.reflect_page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.shub39.reflect.ui.page.isValidTitle
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectEditDialog(
    state: ReflectPageState,
    onEdit: (String, String, LocalTime?) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = state.reflect?.reminder?.hour ?: 0,
        initialMinute = state.reflect?.reminder?.minute ?: 0
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    var title by remember { mutableStateOf(state.reflect?.title!!) }
    var description by remember { mutableStateOf(state.reflect?.description!!) }
    var addReminder by remember { mutableStateOf(state.reflect?.reminder != null) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.edit_reflect),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    singleLine = true,
                    label = { Text(stringResource(R.string.title)) }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = { Text(stringResource(R.string.description)) }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.reminder),
                        style = MaterialTheme.typography.bodyLarge
                    )

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
                        onEdit(
                            title,
                            description,
                            if (addReminder) LocalTime.of(
                                timePickerState.hour,
                                timePickerState.minute
                            ) else null
                        )

                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth(),
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