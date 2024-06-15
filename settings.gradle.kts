rootProject.name = "SpendSense"

include(":shared")
include(":desktop")
include(":android")

dependencyResolutionManagement {
    // Подключение репозиториев, откуда будут тянуться зависимости для подпроектов
    repositories {
        google()
        mavenCentral()
    }
}

// Для настройки Android-таргета
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}