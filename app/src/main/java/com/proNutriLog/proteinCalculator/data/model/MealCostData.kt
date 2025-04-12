package com.proNutriLog.proteinCalculator.data.model

data class MealCostData(
    val mealName: String,
    val foodList: List<ProteinCostData>,
    var isSelected: Boolean = false
) {
}