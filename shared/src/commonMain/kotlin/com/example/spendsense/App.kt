package com.example.spendsense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// common-код, который можно будет запустить из любой платформы:
fun sayHello() {
    println("Hello from common code!")
}

@Composable
fun SayHelloFromCommon() {
    Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
        Text("Hello from common code!")
    }
}