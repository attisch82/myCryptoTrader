import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }

    // iOS targets - must be declared BEFORE sourceSets
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // JVM desktop target
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coil for Compose + network + SVG support
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
                implementation(libs.coil.svg)

                // Ktor core + content negotiation + Kotlinx serialization
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                // Kotlinx Serialization JSON
                implementation(libs.kotlinx.serialization.json)

                // Koin core
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)

                // Kotlinx DateTime
                implementation(libs.kotlinx.datetime)

                //material3
                implementation(libs.material3)

                //sqlDelight
                implementation(libs.runtime)

                //dotenv
                implementation(libs.java.dotenv)

                //viewModel
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.native.driver)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
                implementation(libs.androidx.biometric)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.android.driver)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.cio)
                implementation(libs.sqlite.driver)
            }
        }

        val desktopTest by getting
    }
}

android {
    namespace = "com.attila.mycryptotrader"
    compileSdk = 35
    defaultConfig {
        minSdk = 30
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
