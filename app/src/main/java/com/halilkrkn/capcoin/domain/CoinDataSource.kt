package com.halilkrkn.capcoin.domain

import com.halilkrkn.capcoin.core.util.NetworkError
import com.halilkrkn.capcoin.core.util.Result
import com.halilkrkn.capcoin.domain.models.Coin

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}