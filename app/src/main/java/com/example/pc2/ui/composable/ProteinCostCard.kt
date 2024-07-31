import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight



@Composable
fun ProteinCostCard(
    meal: String,
    servings: String,
    price: String,
    grams: String,
    cal: String,
    costPer50: String,
    costPer1: String,
    servingCost: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header
            Text(
                text = meal,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Data Rows
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Servings: $servings", fontSize = 14.sp)
                    Text("Total $: $price", fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Protein (g): $grams", fontSize = 14.sp)
                    Text("Cals/Serving: $cal", fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Cost/50g: $costPer50", fontSize = 14.sp)
                    Text("Cost/g: $costPer1", fontSize = 14.sp)
                    Text("Cost/Serving: $servingCost", fontSize = 14.sp)
                }
            }
        }
    }
}
