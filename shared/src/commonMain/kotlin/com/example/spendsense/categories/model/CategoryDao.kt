package com.example.spendsense.categories.model

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.spendsense.db.AppDb
import db.categories.CategoryDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class CategoryDao(
    private val db: AppDb,
    private val coroutineContext: CoroutineContext
) {

    private val categoryQueries = db.categoryDbQueries

    fun getAll(): List<Category> =
        categoryQueries
            .getAll()
            .executeAsList()
            .map { it.toEntity() }

    fun getAllFlow(): Flow<List<Category>> =
        categoryQueries
            .getAll()
            .asFlow()
            .mapToList(coroutineContext)
            .map { categories -> categories.map(CategoryDb::toEntity) }

    suspend fun insert(category: Category) = categoryQueries.insert(category.toDb())

    suspend fun insertAll(categories: List<Category>) = categoryQueries.transaction {
        categories.forEach { categoryQueries.insert(it.toDb()) }
    }

    suspend fun delete(id: String) = categoryQueries.delete(id)
}