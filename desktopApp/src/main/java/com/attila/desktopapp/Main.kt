package com.attila.desktopapp

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import com.attila.mycryptotrader.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "MyCryptoTrader Desktop") {
        App()
    }
}