package com.example.spendsense.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

expect val platformModule: Module // Модуль зависимостей, специфичных для платформ

inline fun <reified T> getKoinInstance(qualifier: Qualifier? = null): T {
    return object : KoinComponent {
        val value : T by inject(qualifier) //  KoinComponent.inject достаёт зависимость из графа
    }.value
}

fun initKoin(
    appModule: Module = module {  } // для пробрасывания в граф сущностей, которые создаются платформой (например, Context).
) = startKoin { // Формирование графа зависимостей путём добавления модулей
    modules(
        CoreModule.deviceInfo,
        StorageModule.settings,
        platformModule,
        appModule,
        ViewModelsModule.viewModels,
        RepositoriesModule.repository
    )
}