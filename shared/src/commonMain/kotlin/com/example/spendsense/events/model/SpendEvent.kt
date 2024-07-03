package com.example.spendsense.events.model

import com.example.spendsense.categories.model.Category
import com.example.spendsense.common.ui.calendar.model.CalendarLabel
import com.example.spendsense.extensions.now
import db.events.EventDb
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class SpendEvent(
    val id: String,
    val categoryId: String,
    val title: String,
    val cost: Double,
    val date: LocalDate,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
) {
    companion object {
        val NONE = SpendEvent(
            id = "",
            categoryId = "",
            title = "",
            cost = 0.0,
            date = LocalDate.now(),
            createAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now()
        )

    }
}

fun SpendEvent.toUI(category: Category) = SpendEventUI(
    id = id,
    category = category,
    title = title,
    cost = cost
)

fun SpendEvent.toCalendarLabel(category: Category) = CalendarLabel(
    id = id,
    colorHex = category.colorHex,
    localDate = date
)

fun SpendEvent.toDb() = EventDb(
    id = id,
    categoryId =  categoryId,
    title = title,
    cost = cost,
    date = date,
    createdAt = createAt,
    updatedAt = updateAt
)

fun EventDb.toEntity() = SpendEvent(
    id = id,
    categoryId =  categoryId,
    title = title.orEmpty(),
    cost = cost ?: 0.0,
    date = date,
    createAt = createdAt,
    updateAt = updatedAt
)