package com.example.spendsense.root.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.spendsense.categories.CategoriesScreen
import com.example.spendsense.common.ui.theme.AppTheme
import com.example.spendsense.common.ui.theme.AppThemeProvider
import com.example.spendsense.di.getKoinInstance
import com.example.spendsense.events.EventScreen
import com.example.spendsense.root.RootViewModel
import com.example.spendsense.root.model.AppTab
import com.example.spendsense.settings.compose.SettingsScreen

@Composable
fun RootScreen() {

    val viewModel = getKoinInstance<RootViewModel>()
    val state by viewModel.state.collectAsState()

    AppTheme(
        themeIsDark = state.themeIsDark,
        appPrefs = state.appPrefs
    ) {
        Box(modifier = Modifier.fillMaxSize().background(AppThemeProvider.colors.background)) {
            RootNavigation(state.selectedTab)
            RootBottomBar(state.selectedTab, clickOnTab = viewModel::handleClickOnTab)
        }
    }
}

@Composable
fun BoxScope.RootNavigation(selectedTab: AppTab) {
    when (selectedTab) {
        AppTab.Categories -> CategoriesScreen()
        AppTab.Events -> EventScreen()
        AppTab.Settings -> SettingsScreen(getKoinInstance())
    }
}