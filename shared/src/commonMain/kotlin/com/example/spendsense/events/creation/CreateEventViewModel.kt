package com.example.spendsense.events.creation

import com.example.spendsense.base.BaseEvent
import com.example.spendsense.base.BaseViewModel
import com.example.spendsense.base.BaseViewState
import com.example.spendsense.categories.model.Category
import com.example.spendsense.events.model.SpendEvent
import com.example.spendsense.extensions.now
import com.example.spendsense.platform.randomUUID
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class CreateEventViewModel :
    BaseViewModel<CreateEventViewModel.State, CreateEventViewModel.Event>() {

    fun selectDate(date: LocalDate?) = updateState { copy(date = date ?: LocalDate.now()) }
    fun resetState() = updateState { State.NONE }
    fun changeTitle(title: String) = updateState { copy(title = title) }
    fun changeCost(cost: String) = updateState { copy(cost = cost.toDoubleOrNull() ?: this.cost) }
    fun selectCategory(category: Category) = updateState { copy(category = category) }
    fun finish() {
        val now = LocalDateTime.now()
        val spendEvent = with(state.value) {
            SpendEvent(
                id = randomUUID(),
                title = title,
                cost = cost,
                date = date,
                categoryId = category.id,
                createAt = now,
                updateAt = now
            )
        }
        resetState()
        pushEvent(Event.Finish(spendEvent))
    }

    override fun initialState() = State.NONE

    data class State(
        val title: String,
        val category: Category,
        val date: LocalDate,
        val cost: Double
    ) : BaseViewState {
        companion object {
            val NONE = State(
                title = "",
                category = Category.NONE,
                date = LocalDate.now(),
                cost = 0.0
            )
        }
    }

    sealed interface Event : BaseEvent {
        data class Finish(val spendEvent: SpendEvent) : Event
    }
}