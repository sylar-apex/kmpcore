import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        namespace = "com.psbapp.utils"
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
            baseName = "utils"
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
        outputModuleName.set("composeApp")
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static(rootDirPath)

                    static(projectDirPath)
                }
            }
        }
        binaries.executable()
    }


    sourceSets {
        commonMain.dependencies {
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.kotlin.serialization)
            implementation(libs.kotlinx.datetime)
            implementation(projects.kmpcore.di)
            api(libs.compose.uiUtil)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
           implementation("androidx.core:core-ktx:1.10.1")
       }
        // iOS only: Napier is here to get shared logging into the device's system log via NSLog.
        // The other targets already reach their own console — Logcat on Android, stdout on desktop
        // and the browser console on web — so nothing is gained by routing them through it too.
        iosMain.dependencies {
            implementation(libs.napier)
        }
    }
}

dependencies {
//    implementation(libs.androidx.annotation.jvm)
}
