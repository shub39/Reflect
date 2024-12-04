package com.shub39.reflect.reflect.presentation.reflect_page.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import lib.shub39.heatmaps.calendar.bool.BooleanHeatMap
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsSheet(
    dates: List<LocalDate>,
    onDismiss: () -> Unit,
    onPick: (LocalDate) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.days_clicked),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )

            BooleanHeatMap(
                dates = dates,
                editEnabled = true,
                onClick = onPick,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}