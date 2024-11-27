package com.shub39.reflect.reflect.presentation.reflect_page

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PageImage(
    uri: String,
    bounds: Int
) {
    CoilImage(
        imageModel = { uri },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .size(bounds.dp)
    )
}