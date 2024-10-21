package com.shub39.reflect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shub39.reflect.ui.page.Reflect
import com.shub39.reflect.ui.theme.ReflectTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReflectTheme {
                KoinContext {
                    Reflect()
                }
            }
        }
    }
}