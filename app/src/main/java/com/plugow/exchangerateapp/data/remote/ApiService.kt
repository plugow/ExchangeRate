package com.plugow.exchangerateapp.data.remote

import com.plugow.exchangerateapp.data.local.FXRate
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("latest?base=EUR")
    fun getRates(): Single<FXRate>


}