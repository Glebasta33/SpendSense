package com.example.spendsense.categories.list.compose

import com.example.spendsense.base.BaseViewModel
import com.example.spendsense.base.BaseViewState
import com.example.spendsense.categories.CategoriesRepository
import com.example.spendsense.categories.creation.CreateCategoryData
import com.example.spendsense.categories.creation.toCategory
import com.example.spendsense.categories.model.Category
import com.example.spendsense.categories.list.compose.CategoriesViewModel.State
import com.example.spendsense.extensions.now
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

class CategoriesViewModel(
    private val repository: CategoriesRepository
) : BaseViewModel<State, Nothing>() {

    init {
        activate()
    }

    fun createCategory(
        data: CreateCategoryData
    ) {
        viewModelScope.launch {
            val thisMoment = LocalDateTime.now()
            repository.createCategory(data.toCategory(thisMoment))
        }
    }

    private fun activate() {
        repository.getAllFlow().onEach {
            updateState { copy(categories = it) }
        }.launchIn(viewModelScope)
    }

    data class State(
        val categories: List<Category>
    ) : BaseViewState {
        companion object {
            val NONE = State(emptyList())
        }
    }

    override fun initialState() = State.NONE
}