package com.halilkrkn.capcoin.presentation.coin_list

import com.halilkrkn.capcoin.presentation.models.CoinUI

sealed interface CoinListAction {
    data class OnCoinClick(val coin: CoinUI): CoinListAction
}