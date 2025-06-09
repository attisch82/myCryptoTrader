package com.attila.mycryptotrader.coin.data.remote.impl

import com.attila.mycryptotrader.coin.data.remote.dto.CoinDetailsResponseDto
import com.attila.mycryptotrader.coin.data.remote.dto.CoinPriceHistoryResponseDto
import com.attila.mycryptotrader.coin.data.remote.dto.CoinResponseDto
import com.attila.mycryptotrader.coin.domain.api.CoinRemoteDataSource
import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result
import com.attila.mycryptotrader.core.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val BASE_URL = "https://api.coinranking.com/v2"
class CoinRemoteDataSourceImpl(
    private val client: HttpClient
) : CoinRemoteDataSource {
    override suspend fun getCoinList(): Result<CoinResponseDto, DataError.Remote> =
        safeCall {
            client.get("$BASE_URL/coins")
        }


    override suspend fun getCoinDetails(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote> =
        safeCall {
            client.get("$BASE_URL/coin/$coinId")
        }

    override suspend fun getCoinPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote> =
        safeCall {
            client.get("$BASE_URL/coin/$coinId/history")
        }
}