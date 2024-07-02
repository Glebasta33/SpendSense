package com.example.spendsense.events.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.spendsense.MR
import com.example.spendsense.categories.list.compose.CategoriesListView
import com.example.spendsense.common.ui.atoms.AppButton
import com.example.spendsense.common.ui.atoms.AppTextField
import com.example.spendsense.common.ui.atoms.BottomModalContainer
import com.example.spendsense.common.ui.atoms.TextPairButton
import com.example.spendsense.common.ui.calendar.compose.CalendarColors
import com.example.spendsense.common.ui.calendar.compose.DatePickerView
import com.example.spendsense.common.ui.calendar.model.CalendarDay
import com.example.spendsense.common.ui.theme.AppThemeProvider
import com.example.spendsense.di.DatePickerFactoryQualifier
import com.example.spendsense.di.getKoinInstance
import com.example.spendsense.events.model.SpendEvent
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun CreateEventView(
    isExpand: Boolean,
    selectedDay: CalendarDay?,
    viewModel: CreateEventViewModel,
    createListener: (SpendEvent) -> Unit
) {

    val state by viewModel.state.collectAsState()
    var showCategoriesDialog by remember { mutableStateOf(false) }
    var showDateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(isExpand) {
        if (isExpand) {
            viewModel.selectDate(selectedDay?.date)
        } else {
            viewModel.resetState()
        }

        viewModel.events.onEach {
            when(it) {
                is CreateEventViewModel.Event.Finish -> createListener(it.spendEvent)
            }
        }.launchIn(this)
    }

    BottomModalContainer {
        TextPairButton(
            title = stringResource(MR.strings.category),
            buttonTitle = state.category.title.ifEmpty { stringResource(MR.strings.empty_category) },
            colorHex = state.category.colorHex.takeIf { it.isNotEmpty() }
        ) { showCategoriesDialog = true }

        TextPairButton(
            title = stringResource(MR.strings.date),
            buttonTitle = state.date.toString(),
        ) { showDateDialog = true }

        AppTextField(
            value = state.title,
            placeholder = stringResource(MR.strings.spend_to),
            modifier = Modifier.fillMaxWidth()
        ) { viewModel.changeTitle(it) }

        AppTextField(
            value = state.cost.toString(),
            placeholder = stringResource(MR.strings.cost),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        ) { viewModel.changeCost(it) }

        AppButton(stringResource(MR.strings.save_button)) { viewModel.finish() }
    }

    if (showCategoriesDialog) {
        Dialog(
            onDismissRequest = { showCategoriesDialog = false }
        ) {
            CategoriesListView(
                getKoinInstance(),
                modifier = Modifier.background(
                    AppThemeProvider.colors.surface,
                    RoundedCornerShape(16.dp)
                )
            ) {
                viewModel.selectCategory(it)
                showCategoriesDialog = false
            }
        }
    }

    if (showDateDialog) {
        Dialog(
            onDismissRequest = { showDateDialog = false }
        ) {
            DatePickerView(
                getKoinInstance(DatePickerFactoryQualifier),
                colors = CalendarColors(
                    colorAccent = AppThemeProvider.colors.accent,
                    colorSurface = AppThemeProvider.colors.surface,
                    colorOnSurface = AppThemeProvider.colors.onSurface
                )
            ) {
                viewModel.selectDate(it.date)
                showDateDialog = false
            }
        }
    }
}