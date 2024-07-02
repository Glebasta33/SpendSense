package com.example.spendsense.events.compose

import com.example.spendsense.base.BaseViewModel
import com.example.spendsense.base.BaseViewState
import com.example.spendsense.categories.CategoriesRepository
import com.example.spendsense.categories.model.Category
import com.example.spendsense.common.ui.calendar.model.CalendarDay
import com.example.spendsense.common.ui.calendar.model.CalendarLabel
import com.example.spendsense.events.EventsRepository
import com.example.spendsense.events.compose.EventsViewModel.State
import com.example.spendsense.events.model.SpendEvent
import com.example.spendsense.events.model.SpendEventUI
import com.example.spendsense.events.model.toCalendarLabel
import com.example.spendsense.events.model.toUI
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class EventsViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val eventsRepository: EventsRepository
) : BaseViewModel<State, Nothing>() {

    init {
        activate()
    }

    fun selectDay(day: CalendarDay) = updateState { copy(selectedDay = day) }

    fun createEvent(newEvent: SpendEvent) {
        viewModelScope.launch { eventsRepository.create(newEvent) }
    }

    private fun activate() {
        combine(
            eventsRepository.getAllFlow(),
            categoriesRepository.getAllFlow()
        ) { events, categories ->
            val labels = mapEventsCategoriesToLabels(events, categories)
            updateState {
                copy(
                    events = events,
                    categories = categories,
                    calendarLabels = labels
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun mapEventsCategoriesToLabels(
        events: List<SpendEvent>,
        categories: List<Category>
    ): List<CalendarLabel> {
        return events.map { event ->
            val category = categories.firstOrNull {
                it.id == event.categoryId
            } ?: Category.NONE
            event.toCalendarLabel(category)
        }
    }

    data class State(
        val selectedDay: CalendarDay?,
        val events: List<SpendEvent>,
        val categories: List<Category>,
        val calendarLabels: List<CalendarLabel>
    ) : BaseViewState {
        val eventsByDay: List<SpendEventUI>
            get() = events.filter { it.date == selectedDay?.date }
                .map { spendEvent ->
                    spendEvent.toUI(
                        categories.firstOrNull { it.id == spendEvent.categoryId } ?: Category.NONE
                    )
                }
    }

    override fun initialState() = NONE

    companion object {
        val NONE = State(
            selectedDay = null,
            events = emptyList(),
            categories = emptyList(),
            calendarLabels = emptyList()
        )
    }
}