package com.halilkrkn.capcoin.data.mappers

import com.halilkrkn.capcoin.data.networking.dto.CoinDto
import com.halilkrkn.capcoin.domain.models.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)

fun Coin.toCoinDto() = CoinDto(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)