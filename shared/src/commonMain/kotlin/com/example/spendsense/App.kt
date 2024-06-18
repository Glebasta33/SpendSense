package com.example.spendsense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// common-код, который можно будет запустить из любой платформы:
fun sayHello() {
    println("Hello from common code!")
}

@Composable
fun SayHelloFromCommon() {
    CounterContent()
}

@Composable
private fun CounterContent() {
    var counter by remember { mutableStateOf(0) }

    Column {
        Text(
            text = "Counter: $counter"
        )
        Spacer(Modifier.height(32.dp))
        Button(onClick = { counter++ }) {
            Text("Increase")
        }
    }
}