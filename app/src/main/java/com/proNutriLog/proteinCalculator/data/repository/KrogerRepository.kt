package com.proNutriLog.proteinCalculator.data.repository

import android.util.Log
import com.proNutriLog.proteinCalculator.data.model.ProteinCostData
import com.proNutriLog.proteinCalculator.data.remote.KrogerApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KrogerRepository(private val apiService: KrogerApiService) {

    suspend fun searchItems(query: String): List<ProteinCostData> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.searchItems(query)

            if (response.isSuccessful) {
                val body = response.body()
                // TODO: Map actual response to your ProteinCostData list
                Log.d("KrogerRepo", "Response: $body")
                return@withContext emptyList() // Replace with mapped result
            } else {
                Log.e("KrogerRepo", "Failed: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("KrogerRepo", "Exception: ${e.message}", e)
        }
        return@withContext emptyList()
    }
}
