package com.proNutriLog.proteinCalculator.data.remote

import com.proNutriLog.proteinCalculator.data.model.KrogerProductResponse
import com.proNutriLog.proteinCalculator.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface KrogerApiService {
    @FormUrlEncoded
    @POST("connect/oauth2/token")
    suspend fun getProductAccessToken(
        @Header("Authorization") basicAuth: String,
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("scope") scope: String = "product.compact"
    ): Response<TokenResponse>

    @GET("products")
    suspend fun searchProducts(
        @Header("Authorization") token: String,
        @Query("filter.term") term: String,
        @Query("filter.locationId") locationId: String,
        @Query("filter.limit") limit: Int = 10
    ): Response<KrogerProductResponse>
}

