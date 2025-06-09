package com.attila.mycryptotrader.coin.data.mapper

import com.attila.mycryptotrader.coin.data.remote.dto.CoinItemDto
import com.attila.mycryptotrader.coin.data.remote.dto.CoinPriceDto
import com.attila.mycryptotrader.coin.domain.model.CoinModel
import com.attila.mycryptotrader.coin.domain.model.PriceModel
import com.attila.mycryptotrader.core.domain.coin.Coin

fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(
        uuid = this.uuid,
        name = this.name,
        symbol = this.symbol,
        iconUrl = this.iconUrl
    ),
    price = this.price,
    change = this.change
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = this.price ?: 0.0,
    timestamp = this.timestamp
)