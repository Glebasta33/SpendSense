package com.example.spendsense.root

import com.example.spendsense.base.BaseViewModel
import com.example.spendsense.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel : BaseViewModel<RootContract.State, Nothing>() {

    init {
        SettingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)
    }

    override fun initialState() = RootContract.State.NONE
}