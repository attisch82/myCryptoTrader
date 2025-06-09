package com.attila.mycryptotrader.coin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attila.mycryptotrader.coin.domain.useCase.GetCoinListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.attila.mycryptotrader.core.domain.Result

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinState())
    val state = _state.onStart {
        getCoins()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinState()
    )

    private suspend fun getCoins() {
        when(val result = getCoinsUseCase()){
            is Result.Success -> {
                _state.update {
                    it.copy(
                        coins =result.data.map { coin -> UICoinListItem(
                            uuid = coin.coin.uuid,
                            name = coin.coin.name,
                            symbol = coin.coin.symbol,
                            iconUrl = coin.coin.iconUrl,
                            formattedPrice = coin.price.toString(),
                            formattedChange = coin.change.toString(),
                            isPositiveChange = coin.change >= 0
                        ) }
                    )
                }
            }
            is Result.Failure -> {
                _state.value = _state.value.copy(
                    error = result.error.name
                )
            }
        }
    }
}