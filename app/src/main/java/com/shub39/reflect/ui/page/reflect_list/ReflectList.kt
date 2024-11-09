package com.shub39.reflect.ui.page.reflect_list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReflectList(
    state: HomePageState,
    onNavigate: (id: Long) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.reflects, key = { it.id }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.padding(4.dp))

                        if (it.reminder != null) {
                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        text = it.reminder.format(
                                            DateTimeFormatter.ofPattern("hh:mm a")
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

                    }

                    IconButton(
                        onClick = {
                            onNavigate(it.id)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.round_arrow_forward_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }

    }

}