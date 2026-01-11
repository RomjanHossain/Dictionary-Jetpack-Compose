import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.capx.dictionary"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.capx.dictionary"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
//    kotlinOptions {
//        compileOptions {
//            jvmTarget = JvmTarget.fromTarget("17").toString()
//        }
//    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("11")
    }
}
dependencies {
    implementation(libs.androidx.navigation.compose)
    // Paging 3
    implementation(libs.androidx.paging.runtime.ktx)
    // Paging Compose integration
    implementation(libs.androidx.paging.compose)
    implementation(libs.material3)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    // ksp
    ksp(Google.Dagger.hilt.compiler)
    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // navigation
    implementation(libs.androidx.navigation3.ui)
    // view model
    implementation(AndroidX.lifecycle.viewModel)
    // hilt
    implementation(Google.Dagger.hilt.android)
    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)
    // room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    // trying download manager
    implementation(libs.ktor.client.android)
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // material design
    implementation(libs.androidx.material3)
    // TEST ====================================================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    androidTestImplementation(libs.slf4j.android)
    testImplementation(libs.slf4j.simple)
    testImplementation(libs.turbine)
}