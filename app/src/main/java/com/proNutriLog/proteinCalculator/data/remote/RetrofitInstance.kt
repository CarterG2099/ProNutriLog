package com.proNutriLog.proteinCalculator.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: KrogerApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kroger.com/v1/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KrogerApiService::class.java)
    }
}
