package com.plugow.exchangerateapp.data.local

import android.content.Context
import androidx.annotation.DrawableRes
import com.plugow.exchangerateapp.util.getDrawableRes
import java.util.*

data class ERCurrency(val ctx: Context, val code: String, val rate:Double, var output:Double) {

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