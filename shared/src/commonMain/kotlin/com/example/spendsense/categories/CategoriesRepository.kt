package com.example.spendsense.categories

import com.example.spendsense.categories.model.Category
import kotlinx.coroutines.flow.flow

class CategoriesRepository {

    fun getAllFlow() = flow {
        emit(
            List(20) { i ->
                Category.NONE.copy(
                    id = i.toString(),
                    title = "Category $i"
                )
            }
        )
    }

    suspend fun createCategory(category: Category) = Unit
}