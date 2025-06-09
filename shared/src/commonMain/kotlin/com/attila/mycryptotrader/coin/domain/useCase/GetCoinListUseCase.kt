package com.attila.mycryptotrader.coin.domain.useCase

import com.attila.mycryptotrader.coin.data.mapper.toCoinModel
import com.attila.mycryptotrader.coin.domain.api.CoinRemoteDataSource
import com.attila.mycryptotrader.coin.domain.model.CoinModel
import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result
import com.attila.mycryptotrader.core.domain.map

class GetCoinListUseCase(
    private val source: CoinRemoteDataSource
) {
    suspend operator fun invoke() : Result<List<CoinModel>, DataError.Remote> =
        source.getCoinList().map {
            it.data.data.map { it.toCoinModel() }
        }
}