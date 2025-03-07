package com.halilkrkn.capcoin.presentation.coin_list

import com.halilkrkn.capcoin.core.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}