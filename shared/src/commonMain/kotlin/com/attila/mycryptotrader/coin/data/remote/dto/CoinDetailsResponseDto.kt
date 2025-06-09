package com.attila.mycryptotrader.coin.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsDto(
    val data: CoinPriceDto
)

@Serializable
data class CoinDetailsResponseDto(
    val data: CoinDetailsDto
)
