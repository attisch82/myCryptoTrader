package com.attila.mycryptotrader

import androidx.compose.ui.window.ComposeUIViewController
import com.attila.mycryptotrader.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
){
    App()
}