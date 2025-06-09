package com.attila.mycryptotrader.coin.domain.model

import com.attila.mycryptotrader.core.domain.coin.Coin

data class CoinModel(
    val coin: Coin,
    val price: Double,
    val change: Double
)
