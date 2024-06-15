plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    jvm("desktop") // позволяет более точно указать название платформы: вместо jvmMain будет desktopMain

    // Подключаем мультиплатформенный common-код для текущей платформы
    sourceSets {
        getByName("desktopMain") {// название source set текущей платформы
            dependencies { // подключаем зависимость на common-код
                implementation(project(":shared"))
            }
        }
    }
}

// Конфигурирование Compose точки входа
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}