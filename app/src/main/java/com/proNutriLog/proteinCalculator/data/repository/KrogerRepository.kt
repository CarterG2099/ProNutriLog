package com.proNutriLog.proteinCalculator.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.proNutriLog.proteinCalculator.data.model.KrogerProductResponse
import com.proNutriLog.proteinCalculator.data.remote.KrogerApiService
import javax.inject.Inject

class KrogerRepository @Inject constructor(
    private val apiService: KrogerApiService,
    private val tokenManager: TokenManager
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun searchProducts(term: String, locationId: String): KrogerProductResponse? {
        val token = tokenManager.getValidProductToken()
        val response = apiService.searchProducts(
            token = "Bearer $token",
            term = term,
            locationId = locationId
        )

        return if (response.isSuccessful) {
            response.body()
        } else {
            Log.e("KrogerRepository", "Product search failed: ${response.errorBody()?.string()}")
            null
        }
    }
}


