package com.attila.mycryptotrader.core.domain

sealed interface DataError : Error {
    enum class Remote : DataError {
        TOO_MANY_REQUESTS,
        REQUEST_TIMEOUT,
        SERVER_ERROR,
        UNKNOWN_ERROR,
        SERIALIZATION_ERROR,
        NO_INTERNET_CONNECTION
    }

    enum class Local : DataError {
        DISK_FULL,
        INSUFFICIENT_FUNDS,
        UNKNOWN_ERROR
    }
}