package com.halilkrkn.capcoin.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.halilkrkn.capcoin.core.util.getDrawableIdForCoin
import com.halilkrkn.capcoin.domain.models.Coin
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes
    val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formattedValue: String
)

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formattedValue = formatter.format(this)
    )
}

fun Coin.toCoinUi(): CoinUI {
    return CoinUI(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}