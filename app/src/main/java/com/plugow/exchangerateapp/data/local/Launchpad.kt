package com.plugow.exchangerateapp.data.local

import java.util.*


class FXRate(
    val base: String,
    val date: Date,
    val rates: Map<String, Double>
)