data class ProteinCostData(
    var foodSource: String,
    var servings: Double,
    var grams: Double,
    var price: Double,
    var fiftyGrams: String,
    var oneGram: String,
    var cal: Double,
    var calories: String,
    var isSelected: Boolean = false
) {
    // Calculate the serving cost based on servings and price
    val servingCost: String
        get() = if (servings.toDouble() != 0.0) String.format("%.2f", price / servings) else "N/A"

    override fun toString(): String {
        return "ProteinCostData(foodSource=$foodSource, servings=$servings, grams=$grams, price=$price, fiftyGrams=$fiftyGrams, oneGram=$oneGram, servingCost=$servingCost, isSelected=$isSelected)"
    }
}
