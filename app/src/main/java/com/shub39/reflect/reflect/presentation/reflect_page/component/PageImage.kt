package com.shub39.reflect.reflect.presentation.reflect_page.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shub39.reflect.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PageImage(
    data: Map.Entry<LocalDate, String>,
    bounds: Int
) {
    Box {
        CoilImage(
            imageModel = { data.value },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            previewPlaceholder = painterResource(R.drawable.round_landscape_24),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .size(bounds.dp)
        )

        Card(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = data.key.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}