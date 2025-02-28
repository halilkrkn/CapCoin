package com.halilkrkn.capcoin.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.halilkrkn.capcoin.presentation.models.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(),
    val selectedCoin: CoinUI? = null,
)
