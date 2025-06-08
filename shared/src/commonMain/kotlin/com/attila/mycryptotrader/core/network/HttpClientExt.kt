package com.attila.mycryptotrader.core.network

import com.attila.mycryptotrader.core.domain.DataError
import com.attila.mycryptotrader.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
) : Result<T, DataError.Remote> {
    val response = try {
        execute()
    }catch (e: SocketTimeoutException){
        return Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    }catch (e: UnresolvedAddressException){
        return Result.Failure(DataError.Remote.NO_INTERNET_CONNECTION)
    }catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Failure(DataError.Remote.UNKNOWN_ERROR)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
) = when(response.status.value){
    in 200..299 -> {
        try {
            val responseBody = response.body<T>()
            Result.Success(responseBody)
        }catch (e: Exception){
            Result.Failure(DataError.Remote.SERIALIZATION_ERROR)
        }
    }
    408 -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    429 -> Result.Failure(DataError.Remote.TOO_MANY_REQUESTS)
    in 500..599 -> Result.Failure(DataError.Remote.SERVER_ERROR)
    else -> Result.Failure(DataError.Remote.UNKNOWN_ERROR)
}