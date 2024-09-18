package com.proNutriLog.proteinCalculator

data class ProteinCostData(
    var id: String = "",
    var foodSource: String = "",
    var servings: Double= 0.0,
    var grams: Double = 0.0,
    var price: Double = 0.0,
    var calories: Double = 0.0,
    var isSelected: Boolean = false
) {
    // Calculate the serving cost based on servings and price
    val servingCost: String
        get() = if (servings != 0.0) String.format("%.2f", (price / (grams * servings)) * 100) else "Invalid Data"

    val costPer50: String
        get() = if (grams != 0.0) String.format("%.2f", (price / (grams * servings)) * 50) else "Invalid Data"

    val costPerGram: String
        get() = if (grams != 0.0) String.format("%.2f", price / grams) else "Invalid Data"

    val caloriesPerServing: String
        get() = if (servings != 0.0) String.format("%.2f", (calories / servings)) else "Invalid Data"

    val percentCalFromProtein: String
        get() = if (servings != 0.0) String.format("%.2f", ((grams * 4) / calories) * 100) else "Invalid Data"

    override fun toString(): String {
        return "ProteinCostData(foodSource=$foodSource, servings=$servings, grams=$grams, price=$price, fiftyGrams=$costPer50, oneGram=$costPerGram, calories=$calories, servingCost=$servingCost, isSelected=$isSelected)"
    }
}
