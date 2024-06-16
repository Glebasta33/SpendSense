plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.android.library)
}

// Плагин KMM добавляет в DSL build.gradle.kts targets и source sets.

// С помощью таргетов мы объясняем плагину, с какими платформами предстоит работать.
// Включение таргетов добавляет набора gradle-тасок, которые будут выполнены при сборке проекта.
// С помощью этих задач gradle из Kotlin-кода соберёт код под нужную платформу.

// С помощью source sets мы говорим KMM-плагину, где лежит код для нужной платформы.

kotlin {
    jvm() // KotlinJvmTarget -> добится возможность создавать src дериктории: src/commonMain... (для всех платформ) и src/jvmMain.. (только для JVM-платформы).
    androidTarget()

    listOf(
        iosArm64(), // таргет для реального устройства
        iosX64(), // эмулятор на Intel (?)
        iosSimulatorArm64() // эмулятор на M1
    ).forEach { iosTarget -> // конфигурирование таргетов:
        iosTarget.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        commonMain {// подключаем зависимости к таргету commonMain (а код из commonMain будет доступен на всех платформах).
            dependencies {
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)
            }
        }

        jvmMain {
            dependencies {
                // api - делает зависимость транзитивной, т.е. доступ к этой зависимости будет у модуля, у которого есть зависимость на shared
                api(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = findProperty("app.namespace").toString()
    compileSdk = findProperty("android.compileSdk").toString().toInt()
    
    defaultConfig {
        minSdk = findProperty("android.minSdk").toString().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}