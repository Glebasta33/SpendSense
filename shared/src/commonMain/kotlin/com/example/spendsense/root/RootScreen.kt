package com.example.spendsense.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spendsense.settings.SettingsViewModel
import com.example.spendsense.settings.compose.SettingsScreen

@Composable
fun RootScreen() {
    Box(
        modifier = Modifier.fillMaxSize().padding(36.dp)
    ) {
        SettingsScreen(SettingsViewModel())
    }
}