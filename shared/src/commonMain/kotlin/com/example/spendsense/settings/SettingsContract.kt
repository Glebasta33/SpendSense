package com.example.spendsense.settings

import com.example.spendsense.base.BaseViewState

class SettingsContract {
    data class State(
        val deviceInfo: String,
        val themeIsDark: Boolean
    ): BaseViewState {
        companion object {
            val NONE = State(
                deviceInfo = "",
                themeIsDark = false
            )
        }
    }
}