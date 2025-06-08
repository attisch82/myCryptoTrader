package com.attila.mycryptotrader.core.domain

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Failure<out E: Error>(val error: E) : Result<Nothing, E>
}

inline fun <D, E : Error, R> Result<D, E>.map(transform: (D) -> R) = when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Failure -> this
}

inline fun <D, E: Error> Result<D, E>.onSuccess(action: (D) -> Unit) = when(this){
    is Result.Success -> {
        action(data)
        this
    }
    is Result.Failure -> this
}

inline fun <D, E: Error> Result<D, E>.onFailure(action: (E) -> Unit) = when(this) {
    is Result.Success -> this
    is Result.Failure -> {
        action(error)
        this
    }
}