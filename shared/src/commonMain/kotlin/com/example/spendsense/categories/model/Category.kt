package com.example.spendsense.categories.model

import com.example.spendsense.extensions.now
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
