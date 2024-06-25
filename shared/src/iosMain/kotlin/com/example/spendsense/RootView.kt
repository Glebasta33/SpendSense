package com.example.spendsense

import androidx.compose.ui.window.ComposeUIViewController
import com.example.spendsense.root.RootViewModel
import com.example.spendsense.root.compose.RootScreen

// ComposeUIViewController - обёртка над Compose для работы в SwiftUI
fun MainViewController() = ComposeUIViewController { RootScreen(RootViewModel()) }