plugins {
    //apply false нужен, чтобы плагин применился только к подпроектам и gradle не требовал подключения таргета к root-проекту
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrainsCompose) apply false

    //Плагины для Android target:
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}