package com.example.spendsense.di

import com.example.spendsense.categories.CategoriesRepository
import com.example.spendsense.categories.list.compose.CategoriesViewModel
import com.example.spendsense.categories.model.CategoryDao
import com.example.spendsense.common.ui.calendar.DatePickerViewModel
import com.example.spendsense.db.AppDb
import com.example.spendsense.events.EventsRepository
import com.example.spendsense.events.compose.EventsViewModel
import com.example.spendsense.events.creation.CreateEventViewModel
import com.example.spendsense.events.model.SpendEventDao
import com.example.spendsense.platform.DeviceInfo
import com.example.spendsense.root.RootViewModel
import com.example.spendsense.settings.SettingsViewModel
import com.example.spendsense.storage.DbAdapters
import com.example.spendsense.storage.SettingsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import org.koin.ext.getFullName

object CoreModule { // модули - осн. единицы в Koin, которые содержат зависимости
    val deviceInfo = module {
        single { DeviceInfo() } // single - зависимость будет синглтоном
        factory { Dispatchers.IO + SupervisorJob() }
    }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) } // получение expect-actual сущности. Нужно на стороне каждой платформы создать платформенный модуль для Koin
    }
    val db = module {
        single {
            AppDb(get(), DbAdapters.categoryDbAdapter, DbAdapters.eventDbAdapter)
        }
    }
    val dao = module {
        single { CategoryDao(get(), get()) }
        single { SpendEventDao(get(), get()) }
    }
}

object RepositoriesModule {
    val repository = module {
        single { CategoriesRepository(get()) }
        single { EventsRepository(get()) }
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