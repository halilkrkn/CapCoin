package com.halilkrkn.capcoin.core.data.networking

import com.halilkrkn.capcoin.core.util.NetworkError
import io.ktor.client.statement.HttpResponse
import com.halilkrkn.capcoin.core.util.Result
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }
    return responseToResult(response)
}



/*

coroutineContext.ensureActive() satırı, safeCall fonksiyonu içinde çalıştırılan coroutine'in hala aktif olup olmadığını kontrol eder. Bu satır, coroutine'in iptal edilip edilmediğini kontrol eder ve eğer iptal edilmişse bir CancellationException fırlatır. Bu, coroutine'in iptal edildiği durumlarda gereksiz işlemlerin yapılmasını engeller ve iptal işleminin hızlı bir şekilde gerçekleşmesini sağlar.

 */