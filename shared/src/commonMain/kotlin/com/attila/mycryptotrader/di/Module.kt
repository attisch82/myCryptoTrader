package com.attila.mycryptotrader.di

import com.attila.mycryptotrader.coin.data.remote.impl.CoinRemoteDataSourceImpl
import com.attila.mycryptotrader.coin.domain.api.CoinRemoteDataSource
import com.attila.mycryptotrader.coin.domain.useCase.GetCoinDetailsUseCase
import com.attila.mycryptotrader.coin.domain.useCase.GetCoinListUseCase
import com.attila.mycryptotrader.coin.presentation.CoinListViewModel
import com.attila.mycryptotrader.core.network.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule
        )
    }

expect val platformModule: Module

val sharedModule = module {
    single<HttpClient> { HttpClientFactory.create(get()) }
    viewModel { CoinListViewModel(get()) }
    singleOf(::GetCoinListUseCase)
    singleOf(::CoinRemoteDataSourceImpl).bind<CoinRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
}