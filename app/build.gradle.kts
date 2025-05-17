import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.room)
    alias(libs.plugins.hotreload)
}

val appName = "Reflect"
val appPackageName = "com.shub39.reflect"
val appVersionName = "1.0.0"
val appVersionCode = 1000

android {
    namespace = appPackageName
    compileSdk = 35

    defaultConfig {
        applicationId = appPackageName
        minSdk = 32
        targetSdk = 35
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            resValue("string", "app_name", appName)
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            resValue("string", "app_name", "$appName Debug")
            applicationIdSuffix = ".debug"
        }
        create("beta"){
            resValue("string", "app_name", "$appName Beta")
            applicationIdSuffix = ".beta"
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas")
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.landscapist.coil)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.materialKolor)
            implementation(libs.colorpicker.compose)
            implementation(libs.sqlite.bundled)
            implementation(libs.datetime)
            implementation(libs.aboutLibraries)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.zoomable)
            implementation(libs.composeIcons.fontAwesome)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
        dependencies {
            androidTestImplementation(platform(libs.androidx.compose.bom))
            debugImplementation(libs.androidx.ui.tooling)
            annotationProcessor(libs.androidx.room.room.compiler)
            ksp(libs.androidx.room.room.compiler)
        }
    }
}

aboutLibraries {
    // Remove the "generated" timestamp to allow for reproducible builds; from kaajjo/LibreSudoku
    export.excludeFields.add("generated")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

compose.desktop {
    application {
        mainClass = "com.shub39.reflect.MainKt"
    }
}

composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}

tasks.register<ComposeHotRun>("runHot") {
    mainClass.set("com.shub39.reflect.MainKt")
}