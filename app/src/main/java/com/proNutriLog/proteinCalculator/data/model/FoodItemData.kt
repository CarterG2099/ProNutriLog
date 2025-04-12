package com.proNutriLog.proteinCalculator.data.model

data class FoodItem(
    val item_id: Long,
    val name: String,
    val category: String,
    val nutrition_info_id: Long // Foreign key referencing nutrition_info table
)
