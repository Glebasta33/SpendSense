package com.example.spendsense.categories.creation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.spendsense.common.ui.theme.AppThemeProvider

@Composable
fun ColorSlider(
    color: Color,
    sliderValue: Float,
    onValueChanged: (Float) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(30.dp).background(color, RoundedCornerShape(16.dp)))

        Spacer(Modifier.height(8.dp))

        Slider(
            value = sliderValue,
            onValueChange = { onValueChanged.invoke(it) },
            colors = SliderDefaults.colors(
                thumbColor = AppThemeProvider.colors.accent,
                activeTrackColor = AppThemeProvider.colors.accent.copy(alpha = 0.8f),
                inactiveTrackColor = AppThemeProvider.colors.onSurface
            )
        )
    }
}