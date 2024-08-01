import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProteinCostCard(foodItem: ProteinCostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Use CardDefaults for elevation
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header
            Text(
                text = foodItem.foodSource,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Data Rows
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Servings: ${foodItem.servings}", fontSize = 14.sp)
                    Text("Total $: ${foodItem.price}", fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Protein (g): ${foodItem.grams}", fontSize = 14.sp)
                    Text("Cals/Serving: ${foodItem.cal}", fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Cost/50g: ${foodItem.fiftyGrams}", fontSize = 14.sp)
                    Text("Cost/g: ${foodItem.oneGram}", fontSize = 14.sp)
                    Text("Cost/Serving: ${foodItem.servingCost}", fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun ProteinCostList(foodItems: List<ProteinCostData>) {
    LazyColumn {
        items(foodItems) { item ->
            ProteinCostCard(foodItem = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProteinCostList() {
    // Provide a list of sample data for the preview
    val sampleFoodItems = listOf(
        ProteinCostData(
            foodSource = "Chicken Breast",
            servings = 200.0,
            grams = 150.0,
            price = 5.99,
            fiftyGrams = "1.50",
            oneGram = "0.03",
            cal = 120.0,
            calories = "80"
        ),
        ProteinCostData(
            foodSource = "Salmon",
            servings = 150.0,
            grams = 200.0,
            price = 8.99,
            fiftyGrams = "2.00",
            oneGram = "0.04",
            cal = 150.0,
            calories = "75"
        )
    )

    ProteinCostList(foodItems = sampleFoodItems)
}
