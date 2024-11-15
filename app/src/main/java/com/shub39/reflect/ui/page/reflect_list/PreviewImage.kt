package com.shub39.reflect.ui.page.reflect_list

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
fun PreviewImage(uri: String) {
    CoilImage(
        imageModel = { uri },
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        ),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .size(height = 100.dp, width = 50.dp)
    )
}