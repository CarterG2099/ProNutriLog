package com.proNutriLog.proteinCalculator

data class MealCostData(
    val mealName: String,
    val foodList: List<ProteinCostData>,
    var isSelected: Boolean = false
) {
}