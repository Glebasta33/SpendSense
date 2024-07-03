package com.example.spendsense.categories

import com.example.spendsense.categories.model.Category
import com.example.spendsense.categories.model.CategoryDao

class CategoriesRepository(
    private val categoryDao: CategoryDao
) {

    fun getAllFlow() = categoryDao.getAllFlow()

    suspend fun createCategory(category: Category) = categoryDao.insert(category)
}