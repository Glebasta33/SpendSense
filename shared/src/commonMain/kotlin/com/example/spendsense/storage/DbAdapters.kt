package com.example.spendsense.storage

import app.cash.sqldelight.ColumnAdapter
import db.categories.CategoryDb
import db.events.EventDb
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

/**
 * Адаптеры нужны, чтобы помочь SQLDelight в работе с неизвестными типами данных.
 */
object DbAdapters {
    val categoryDbAdapter = CategoryDb.Adapter( // в таблице 2 поля, которые нужно преобразовать:
        LocalDateTimeAdapter, LocalDateTimeAdapter
    )
    val eventDbAdapter = EventDb.Adapter(  // в таблице 3 поля, которые нужно преобразовать:
        LocalDateAdapter, LocalDateTimeAdapter, LocalDateTimeAdapter
    )
}

object LocalDateTimeAdapter : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime = databaseValue.toLocalDateTime()

    override fun encode(value: LocalDateTime): String = value.toString()
}

object LocalDateAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate = databaseValue.toLocalDate()

    override fun encode(value: LocalDate): String = value.toString()
}