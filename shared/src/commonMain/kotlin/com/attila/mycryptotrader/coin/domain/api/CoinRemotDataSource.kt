package com.attila.mycryptotrader.coin.domain.api

import com.attila.mycryptotrader.coin.data.remote.dto.CoinDetailsResponseDto
import com.attila.mycryptotrader.coin.data.remote.dto.CoinPriceHistoryResponseDto
import com.attila.mycryptotrader.coin.data.remote.dto.CoinResponseDto
import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result

interface CoinRemoteDataSource {
    suspend fun getCoinList(): Result<CoinResponseDto, DataError.Remote>
    suspend fun getCoinDetails(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote>
    suspend fun getCoinPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote>
}