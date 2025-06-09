package com.attila.mycryptotrader.coin.domain.useCase

import com.attila.mycryptotrader.coin.data.mapper.toPriceModel
import com.attila.mycryptotrader.coin.domain.api.CoinRemoteDataSource
import com.attila.mycryptotrader.coin.domain.model.PriceModel
import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result
import com.attila.mycryptotrader.core.domain.map

class GetCoinPriceHistoryUseCase(
    private val source: CoinRemoteDataSource
) {
    suspend operator fun invoke(coinId: String) : Result<List<PriceModel>, DataError.Remote> =
        source.getCoinPriceHistory(coinId = coinId).map { dto ->
            dto.data.data.map { it.toPriceModel() }
        }
}