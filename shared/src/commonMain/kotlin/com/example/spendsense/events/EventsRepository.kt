package com.example.spendsense.events

import com.example.spendsense.events.model.SpendEvent
import com.example.spendsense.events.model.SpendEventDao

class EventsRepository(
    private val dao: SpendEventDao
) {

    fun getAllFlow() = dao.getAllFlow()

    suspend fun create(spendEvent: SpendEvent) = dao.insert(spendEvent)

}