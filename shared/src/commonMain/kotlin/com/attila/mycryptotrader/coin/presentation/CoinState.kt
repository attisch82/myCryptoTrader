package com.attila.mycryptotrader.coin.presentation

data class CoinState(
    val error: String = "",
    val coins: List<UICoinListItem> = emptyList(),
)
