package com.plugow.exchangerateapp.data.local

import java.util.*


class FXRate(
    val base: String,
    val date: Date,
    val rates: Map<String, Double>
)

//class Rates(
//    val AUD:Double = 0.0,
//    val BGN:Double = 0.0,
//    val BRL:Double = 0.0,
//    val CAD:Double = 0.0,
//    val CHF:Double = 0.0,
//    val CNY:Double = 0.0,
//    val CZK:Double = 0.0,
//    val DKK:Double = 0.0,
//    val GBP:Double = 0.0,
//    val HKD:Double = 0.0,
//    val HRK:Double = 0.0,
//    val HUF:Double = 0.0,
//    val IDR:Double = 0.0,
//    val ILS:Double = 0.0,
//    val INR:Double = 0.0,
//    val ISK:Double = 0.0,
//    val JPY:Double = 0.0,
//    val KRW:Double = 0.0,
//    val MXN:Double = 0.0,
//    val MYR:Double = 0.0,
//    val NOK:Double = 0.0,
//    val NZD:Double = 0.0,
//    val PHP:Double = 0.0,
//    val PLN:Double = 0.0,
//    val RON:Double = 0.0,
//    val RUB:Double = 0.0,
//    val SEK:Double = 0.0,
//    val SGD:Double = 0.0,
//    val THB:Double = 0.0,
//    val TRY:Double = 0.0,
//    val USD:Double = 0.0,
//    val ZAR:Double = 0.0
//)