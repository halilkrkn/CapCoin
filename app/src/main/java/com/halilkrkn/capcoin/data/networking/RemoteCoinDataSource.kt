package com.halilkrkn.capcoin.data.networking

import com.halilkrkn.capcoin.core.data.networking.constructUrl
import com.halilkrkn.capcoin.core.data.networking.safeCall
import com.halilkrkn.capcoin.core.util.NetworkError
import com.halilkrkn.capcoin.core.util.Result
import com.halilkrkn.capcoin.core.util.map
import com.halilkrkn.capcoin.data.mappers.toCoin
import com.halilkrkn.capcoin.data.networking.dto.CoinsResponseDto
import com.halilkrkn.capcoin.domain.CoinDataSource
import com.halilkrkn.capcoin.domain.models.Coin
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient,
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { coinDto ->
                coinDto.toCoin()
            }
        }
    }
}