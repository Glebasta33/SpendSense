package com.example.spendsense

import androidx.compose.ui.window.ComposeUIViewController
import com.example.spendsense.root.RootScreen

// ComposeUIViewController - обёртка над Compose для работы в SwiftUI
fun MainViewController() = ComposeUIViewController { RootScreen() }