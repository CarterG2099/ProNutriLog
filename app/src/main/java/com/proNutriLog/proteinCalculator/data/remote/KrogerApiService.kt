package com.proNutriLog.proteinCalculator.data.remote

import com.proNutriLog.proteinCalculator.data.model.KrogerSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KrogerApiService {
    @GET("products")
    suspend fun searchItems(@Query("filter.term") query: String): Response<KrogerSearchResponse>
}
