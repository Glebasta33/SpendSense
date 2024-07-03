package com.example.spendsense.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.spendsense.db.AppDb
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val platformModule: Module = module {
    single<SqlDriver> { NativeSqliteDriver(AppDb.Schema, "AppDb") }
}

object IosKoin {
    fun initialize(
        userDefaults: NSUserDefaults
    ) = initKoin(
        appModule = module {
            single<Settings> {
                NSUserDefaultsSettings(userDefaults)
            }
        }
    )
}