import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        namespace = "com.psbapp.networking"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(libs.versions.javaVersion.get())
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "networking"
            isStatic = true
        }
    }

    jvm("desktop")

    // Plain Kotlin/JS so the Compose-free layers of the app can be consumed by a React front end.
    js {
        browser()
        binaries.library()
    }


    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }


    sourceSets {
        androidMain.dependencies {
            api (libs.ktor.client.android.engine)
            api (libs.ktor.client.android)
        }
        iosMain.dependencies {
            api(libs.ktor.client.ios)
            api(libs.ktor.client.ios.darwin)
        }
        commonMain.dependencies {
            api(libs.kotlin.serialization)
            // ktor
            api (libs.ktor.client.core)
            api(libs.ktor.client.serializer)
            api(libs.ktor.client.auth)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging) // enable http logging
            api(libs.ktor.json.serializer)
            api(projects.kmpcore.utils)
            api(projects.kmpcore.di)
            api(libs.ktor.client.websockets)
            api(projects.kmpcore.storage.datastore)
        }
        jsMain.dependencies {
            api(libs.ktor.client.js)
        }
        val desktopMain by getting
        desktopMain.dependencies {
            api(libs.ktor.client.cio)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
