package com.shub39.reflect.reflect.presentation.reflect_list.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
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

@Composable
fun PreviewImage(data: Map.Entry<LocalDate, String>) {
    CoilImage(
        imageModel = { data.value },
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        ),
        previewPlaceholder = painterResource(R.drawable.round_landscape_24),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .size(height = 100.dp, width = 50.dp)
    )
}