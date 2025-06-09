package com.attila.mycryptotrader.coin.presentation

data class UICoinListItem(
    val uuid: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val formattedPrice: String,
    val formattedChange: String,
    val isPositiveChange: Boolean
)
