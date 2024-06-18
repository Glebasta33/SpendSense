package com.example.spendsense.settings

import com.example.spendsense.base.BaseViewModel
import com.example.spendsense.platform.DeviceInfo
import com.example.spendsense.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SettingsViewModel : BaseViewModel<SettingsContract.State, Nothing>() {

    init {
        SettingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)

        val deviceInfo = DeviceInfo()
        updateState { copy(deviceInfo = deviceInfo.getSummary()) }
    }

    override fun initialState() = SettingsContract.State.NONE

    fun switchTheme(isDark: Boolean) {
        SettingsManager.themeIsDark = isDark
    }
}