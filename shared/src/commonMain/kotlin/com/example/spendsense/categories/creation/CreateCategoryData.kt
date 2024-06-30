package com.example.spendsense.categories.creation

import com.example.spendsense.categories.model.Category
import com.example.spendsense.platform.randomUUID
import kotlinx.datetime.LocalDateTime

data class CreateCategoryData(
    val title: String,
    val subtitle: String,
    val colorHex: String
)

fun CreateCategoryData.toCategory(dateTime: LocalDateTime) = Category(
    id = randomUUID(),
    title = title,
    description = subtitle,
    colorHex = colorHex,
    createAt = dateTime,
    updateAt = dateTime
)