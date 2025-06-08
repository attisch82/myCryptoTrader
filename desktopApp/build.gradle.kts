plugins {
    kotlin("jvm")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    application
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}

application {
    mainClass.set("com.attila.desktopapp.MainKt")
}

kotlin {
    jvmToolchain(21)
}
