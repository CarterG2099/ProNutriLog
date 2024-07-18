package com.example.pc2

import ProteinCostData

data class MealCostData(
    val mealName: String,
    val foodList: List<ProteinCostData>,
    var isSelected: Boolean = false
) {
/*    var mealPrice: Double,
    var mealGrams: Double,
    var mealCals: Double,
    var mealPercentage: Double,

    // Override toString() to provide a useful representation
    override fun toString(): String {
        return "MealData(Meal=$mealName, Price=$mealPrice, grams=$mealGrams, Calories=$mealCals, Percentages=$mealPercentage)"
    }

    init {
        if (foodList.isNotEmpty()) {
            mealPrice = foodList.sumByDouble { it.price.toDoubleOrNull() ?: 0.0 }
            mealGrams = foodList.sumByDouble { it.grams.toDoubleOrNull() ?: 0.0 }
            mealCals = foodList.sumByDouble { it.cal.toDoubleOrNull() ?: 0.0 }
            mealPercentage = (mealGrams * 4) / mealCals * 100
        }
    }*/
}