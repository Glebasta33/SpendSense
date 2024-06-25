package com.example.spendsense.storage

import android.content.Context
import com.example.spendsense.App
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class AppSettings actual constructor() {
    actual val settings: Settings = SharedPreferencesSettings(
        App.instance.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    )
}