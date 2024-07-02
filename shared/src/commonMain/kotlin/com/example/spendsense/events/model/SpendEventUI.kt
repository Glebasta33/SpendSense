package com.example.spendsense.events.model

import com.example.spendsense.categories.model.Category

data class SpendEventUI(
    val id: String,
    val category: Category,
    val title: String,
    val cost: Double
)
