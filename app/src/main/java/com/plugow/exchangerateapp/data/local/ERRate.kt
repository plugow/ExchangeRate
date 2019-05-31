package com.plugow.exchangerateapp.data.local

import java.util.*


class ERRate(
    val base: String,
    val date: Date,
    val rates: Map<String, Double>
)