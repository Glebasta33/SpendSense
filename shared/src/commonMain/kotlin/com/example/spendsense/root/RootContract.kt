package com.example.spendsense.root

import com.example.spendsense.base.BaseViewState
import com.example.spendsense.common.ui.AppPrefs

class RootContract {

    data class State(
        val themeIsDark: Boolean,
        val firstDayIsMonday: Boolean
    ) : BaseViewState {

        val appPrefs: AppPrefs
            get() = AppPrefs(firstDayIsMonday = firstDayIsMonday)

        companion object {
            val NONE = State(
                themeIsDark = true,
                firstDayIsMonday = true
            )
        }
    }
}