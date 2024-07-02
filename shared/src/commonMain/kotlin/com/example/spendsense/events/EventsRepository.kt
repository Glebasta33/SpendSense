package com.example.spendsense.events

import com.example.spendsense.events.model.SpendEvent
import kotlinx.coroutines.flow.flow

class EventsRepository {

    fun getAllFlow() = flow { emit(SpendEvent.getStubs()) }

    fun create(spendEvent: SpendEvent) = Unit

}