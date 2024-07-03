package com.example.spendsense.categories.model

import com.example.spendsense.extensions.now
import db.categories.CategoryDb
import kotlinx.datetime.LocalDateTime

data class Category(
    val id: String,
    val title: String,
    val description: String,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val colorHex: String
) {
    companion object {
        val NONE = Category(
            id = "NONE_CATEGORY",
            title = "",
            description = "",
            createAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now(),
            colorHex = ""
        )
    }
}

fun CategoryDb.toEntity() = Category(
    id = id,
    title = title.orEmpty(),
    description = description.orEmpty(),
    createAt = createdAt,
    updateAt = updatedAt,
    colorHex = colorHex
)

fun Category.toDb() = CategoryDb(
    id = id,
    title = title,
    description = description,
    createdAt = createAt,
    updatedAt = updateAt,
    colorHex = colorHex
)