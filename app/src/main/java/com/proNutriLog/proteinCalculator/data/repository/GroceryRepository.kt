package com.proNutriLog.proteinCalculator.data.repository


interface GroceryRepository {
    suspend fun searchProducts(term: String, locationId: String)
}
