package com.example.spendsense.info

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.spendsense.platform.DeviceInfo

@Composable
fun DeviceInfoScreen() {
    Text(DeviceInfo().getSummary())
}