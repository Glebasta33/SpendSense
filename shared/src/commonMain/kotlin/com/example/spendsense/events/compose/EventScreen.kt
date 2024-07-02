package com.example.spendsense.events.compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.example.spendsense.common.ui.atoms.FAB
import com.example.spendsense.common.ui.atoms.RootBox
import com.example.spendsense.common.ui.calendar.compose.CalendarColors
import com.example.spendsense.common.ui.calendar.compose.DatePickerView
import com.example.spendsense.common.ui.theme.AppThemeProvider
import com.example.spendsense.di.DatePickerSingleQualifier
import com.example.spendsense.di.getKoinInstance
import com.example.spendsense.events.creation.CreateEventView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoxScope.EventScreen(
    viewModel: EventsViewModel
) {

    val sheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
           CreateEventView(
               isExpand = sheetState.currentValue == ModalBottomSheetValue.Expanded,
               selectedDay = state.selectedDay,
               viewModel = getKoinInstance(),
               createListener = {
                   viewModel.createEvent(newEvent = it)
                   scope.launch { sheetState.hide() }
               }
           )
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.zIndex(1f)
    ) {
        RootBox {
            Column {
                DatePickerView(
                    viewModel = getKoinInstance(DatePickerSingleQualifier),
                    colors = CalendarColors.default.copy(
                        colorSurface = AppThemeProvider.colors.surface,
                        colorOnSurface = AppThemeProvider.colors.onSurface,
                        colorAccent = AppThemeProvider.colors.accent
                    ),
                    firstDayIsMonday = AppThemeProvider.appPrefs.firstDayIsMonday,
                    labels = state.calendarLabels,
                    selectDayListener = viewModel::selectDay
                )

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.eventsByDay) { event ->
                        SpendEventItem(event)
                    }
                }
            }
            FAB { scope.launch { sheetState.show() } }
        }
    }

}