package com.example.spendsense.di

import com.example.spendsense.platform.DeviceInfo
import com.example.spendsense.root.RootViewModel
import com.example.spendsense.settings.SettingsViewModel
import com.example.spendsense.storage.SettingsManager
import org.koin.dsl.module

object CoreModule { // модули - осн. единицы в Koin, которые содержат зависимости
    val deviceInfo = module {
        single { DeviceInfo() } // single - зависимость будет синглтоном
    }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) } // получение expect-actual сущности. Нужно на стороне каждой платформы создать платформенный модуль для Koin
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
    }
}