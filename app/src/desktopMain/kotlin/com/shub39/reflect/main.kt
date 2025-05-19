package com.shub39.reflect

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.shub39.reflect.app.Reflect

fun main() {

    singleWindowApplication(
        state = WindowState(size = DpSize(400.dp, 900.dp)),
        title = "Hot Reload"
    ) {
        Reflect()
    }
}