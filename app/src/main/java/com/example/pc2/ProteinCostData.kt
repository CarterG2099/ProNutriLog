data class ProteinCostData(
    val foodSource: String,
    val servings: Double,
    val grams: Double,
    val price: Double,
    val fiftyGrams: String,
    val oneGram: String,
    val cal: Double,
    val calories: String,
    val isSelected: Boolean = false
) {
    // Calculate the serving cost based on servings and price
    val servingCost: String
        get() = if (servings.toDouble() != 0.0) String.format("%.2f", price / servings) else "N/A"

    override fun toString(): String {
        return "ProteinCostData(foodSource=$foodSource, servings=$servings, grams=$grams, price=$price, fiftyGrams=$fiftyGrams, oneGram=$oneGram, servingCost=$servingCost, isSelected=$isSelected)"
    }
}
