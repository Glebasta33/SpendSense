package com.example.spendsense

import androidx.compose.ui.window.ComposeUIViewController

// ComposeUIViewController - обёртка над Compose для работы в SwiftUI
fun MainViewController() = ComposeUIViewController { SayHelloFromCommon() }