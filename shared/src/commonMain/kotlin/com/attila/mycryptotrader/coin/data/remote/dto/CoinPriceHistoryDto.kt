package com.attila.mycryptotrader.coin.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val price: Double?,
    val timestamp: Long
)

@Serializable
data class CoinPriceHistoryDto(
    val data: List<CoinPriceDto>
)

@Serializable
data class CoinPriceHistoryResponseDto(
    val data: CoinPriceHistoryDto
)