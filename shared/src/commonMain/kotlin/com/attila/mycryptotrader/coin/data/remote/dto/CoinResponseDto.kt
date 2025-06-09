package com.attila.mycryptotrader.coin.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinItemDto(
    val uuid: String,
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val price: Double,
    val rank: Int,
    val change: Double
)

@Serializable
data class CoinListDto(
    val data: List<CoinItemDto>
)

@Serializable
data class CoinResponseDto(
    val data: CoinListDto
)