plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion = "1.0.0-beta08"

dependencies {
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha02")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.england.android"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}
