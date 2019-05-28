package com.plugow.exchangerateapp.data.local

import android.content.Context
import androidx.annotation.DrawableRes
import com.plugow.exchangerateapp.util.getDrawableRes
import java.util.*

class ERCurrency(ctx: Context, val code: String, val rate:Double) {

    val displayName: String

    val symbol: String

    @DrawableRes
    val flagResource: Int

    init {
        val currency = Currency.getInstance(code)
        displayName = currency.displayName
        symbol = currency.symbol
        flagResource = currency.getDrawableRes(ctx)
    }
}