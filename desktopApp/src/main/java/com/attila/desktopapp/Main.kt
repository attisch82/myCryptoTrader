package com.attila.desktopapp

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import com.attila.mycryptotrader.App
import com.attila.mycryptotrader.di.initKoin


fun main() = application {
    initKoin()
    Window(onCloseRequest = ::exitApplication, title = "MyCryptoTrader Desktop") {
        App()
    }
}