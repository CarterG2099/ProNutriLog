data class ProteinCostData(
    var foodSource: String,
    var servings: String,
    var grams: String,
    var price: String,
    var fiftyGrams: String,
    var oneGram: String,
    var cal: String,
    var calories: String,
    var isSelected: Boolean = false
) {
    private val servingsDouble: Double = servings.toDoubleOrNull() ?: 0.0
    private val priceDouble: Double = price.toDoubleOrNull() ?: 0.0
    var servingCost = String.format("%.2f", priceDouble / servingsDouble)


    override fun toString(): String {
        return "ProteinCostData(foodSource=$foodSource, servings=$servings, grams=$grams, price=$price, fiftyGrams=$fiftyGrams, oneGram=$oneGram, servingCost=$servingCost, isSelected=$isSelected)"
    }
}
