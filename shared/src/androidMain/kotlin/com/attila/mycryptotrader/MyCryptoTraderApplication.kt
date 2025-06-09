package com.attila.mycryptotrader

import android.app.Application
import com.attila.mycryptotrader.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MyCryptoTraderApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MyCryptoTraderApplication)
        }
    }
}