package com.proNutriLog.proteinCalculator.data.model

data class Price(
    val price_id: Long,
    val item_id: Long, // Foreign key referencing FoodItem table
    val store_id: Long, // Foreign key referencing Store table
    val price: Double,
    val timestamp: String // The timestamp should match the type of `timestamp` in your database, typically a string representation of the date
)
