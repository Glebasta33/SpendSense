package com.example.spendsense.events

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.example.spendsense.common.ui.calendar.compose.CalendarColors
import com.example.spendsense.common.ui.calendar.compose.DatePickerView
import com.example.spendsense.common.ui.theme.AppThemeProvider
import com.example.spendsense.di.getKoinInstance

@Composable
fun BoxScope.EventScreen() {
    DatePickerView(
        viewModel = getKoinInstance(),
        colors = CalendarColors.default.copy(
            colorSurface = AppThemeProvider.colors.surface,
            colorOnSurface = AppThemeProvider.colors.onSurface,
            colorAccent = AppThemeProvider.colors.accent
        ),
        firstDayIsMonday = AppThemeProvider.appPrefs.firstDayIsMonday,
        labels = emptyList(),
        selectDayListener = { day -> }
    )
}