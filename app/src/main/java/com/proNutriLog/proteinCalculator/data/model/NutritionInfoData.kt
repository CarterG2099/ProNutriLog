package com.proNutriLog.proteinCalculator.data.model

data class NutritionInfo(
    val nutrition_info_id: Long,
    val calories: Long,
    val protein: Long,
    val fat: Long,
    val carbs: Long,
    val gluten_free: Boolean,
    val dairy_free: Boolean,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val carnivore: Boolean
)
