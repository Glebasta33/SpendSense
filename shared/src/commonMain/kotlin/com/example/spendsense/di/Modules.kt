package com.example.spendsense.di

import com.example.spendsense.categories.CategoriesRepository
import com.example.spendsense.categories.list.compose.CategoriesViewModel
import com.example.spendsense.common.ui.calendar.DatePickerViewModel
import com.example.spendsense.events.EventsRepository
import com.example.spendsense.events.compose.EventsViewModel
import com.example.spendsense.events.creation.CreateEventViewModel
import com.example.spendsense.platform.DeviceInfo
import com.example.spendsense.root.RootViewModel
import com.example.spendsense.settings.SettingsViewModel
import com.example.spendsense.storage.SettingsManager
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import org.koin.ext.getFullName

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

object RepositoriesModule {
    val repository = module {
        single { CategoriesRepository() }
        single { EventsRepository() }
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
        single(DatePickerSingleQualifier) { DatePickerViewModel() }
        factory(DatePickerFactoryQualifier) { DatePickerViewModel() }
        single { CategoriesViewModel(get()) }
        single { EventsViewModel(get(), get()) }
        single { CreateEventViewModel() }
    }
}

object DatePickerSingleQualifier : Qualifier  {
    override val value: QualifierValue
        get() = this::class.getFullName()
}

object DatePickerFactoryQualifier : Qualifier  {
    override val value: QualifierValue
        get() = this::class.getFullName()
}