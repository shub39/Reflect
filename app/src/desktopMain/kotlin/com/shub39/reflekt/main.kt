package com.shub39.reflekt

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.shub39.reflekt.app.Reflekt

fun main() {

    singleWindowApplication(
        state = WindowState(size = DpSize(400.dp, 900.dp)),
        title = "Hot Reload"
    ) {
        Reflekt()
    }
}