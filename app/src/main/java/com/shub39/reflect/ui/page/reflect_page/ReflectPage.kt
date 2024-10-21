package com.shub39.reflect.ui.page.reflect_page

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shub39.reflect.data.Reflect
import java.time.LocalDate

@Composable
fun ReflectPage(
    state: ReflectPageState,
    action: (ReflectPageAction) -> Unit
) {
    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri->
        if (uri != null) {
            action(ReflectPageAction.OnAdd(uri))
        }
    }

    if (state.reflect == null) {
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
                textAlign = TextAlign.Center
            )

            Text(
                text = state.reflect.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ReflectPagePreview() {
    val state = ReflectPageState(
        reflect = Reflect(
            id = 1,
            title = "Reflect",
            description = "Reflect description",
            start = LocalDate.now()
        ),
        filePaths = emptyList()
    )

    ReflectPage(
        state = state,
        action = {}
    )
}