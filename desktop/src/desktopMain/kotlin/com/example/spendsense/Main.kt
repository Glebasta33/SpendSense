package com.example.spendsense

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.spendsense.root.RootScreen

fun main() {
    sayHello()

    application {
        val state = rememberWindowState().apply { size = DpSize(400.dp, 850.dp) }

        Window(
            onCloseRequest = { exitApplication() },
            state = state,
            title = "Spend Sense"
        ) {
            RootScreen()
        }
    }
}