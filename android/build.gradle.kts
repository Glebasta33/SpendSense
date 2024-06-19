plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = findProperty("app.namespace").toString()
    compileSdk = findProperty("android.compileSdk").toString().toInt()

    defaultConfig {
        minSdk = findProperty("android.minSdk").toString().toInt()
        targetSdk = findProperty("android.targetSdk").toString().toInt()
        applicationId = "com.example.spendsense"
        versionCode = 1
        versionName = "0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.4"
        }
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        implementation(libs.androidx.activity.compose)
        implementation(project(":shared"))
    }
}

