package com.attila.mycryptotrader.coin.domain.useCase

import com.attila.mycryptotrader.coin.data.mapper.toCoinModel
import com.attila.mycryptotrader.coin.domain.api.CoinRemoteDataSource
import com.attila.mycryptotrader.coin.domain.model.CoinModel
import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result
import com.attila.mycryptotrader.core.domain.map

class GetCoinDetailsUseCase(
    private val source: CoinRemoteDataSource
) {
    suspend operator fun invoke(coinId: String) : Result<CoinModel, DataError.Remote> =
        source.getCoinDetails(coinId = coinId).map { it.data.data.toCoinModel() }
}