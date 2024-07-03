package com.example.spendsense.events.model

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.spendsense.db.AppDb
import db.events.EventDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class SpendEventDao(
    private val db: AppDb,
    private val coroutineContext: CoroutineContext
) {
    
    private val eventsQueries = db.eventDbQueries

    fun getAll(): List<SpendEvent> =
        eventsQueries
            .getAll()
            .executeAsList()
            .map { it.toEntity() }

    fun getAllFlow(): Flow<List<SpendEvent>> =
        eventsQueries
            .getAll()
            .asFlow()
            .mapToList(coroutineContext)
            .map { categories -> categories.map(EventDb::toEntity) }

    suspend fun insert(event: SpendEvent) = eventsQueries.insert(event.toDb())

    suspend fun insertAll(events: List<SpendEvent>) = eventsQueries.transaction {
        events.forEach { eventsQueries.insert(it.toDb()) }
    }

    suspend fun delete(id: String) = eventsQueries.delete(id)
}