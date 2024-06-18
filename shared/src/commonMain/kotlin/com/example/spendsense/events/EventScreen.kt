package com.example.spendsense.events

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BoxScope.EventScreen() {
    Text("Event", modifier = Modifier.align(Alignment.Center))
}