import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun EditScreen(
    foodItem: ProteinCostData,
    onDismiss: () -> Unit,
    onSave: (ProteinCostData) -> Unit
) {

    // Define state for each text field
    var foodText by remember { mutableStateOf(foodItem.foodSource) }
    var servingsText by remember { mutableStateOf(foodItem.servings.toString()) }
    var gramsText by remember { mutableStateOf(foodItem.grams.toString()) }
    var priceText by remember { mutableStateOf(foodItem.price.toString()) }
    var caloriesText by remember { mutableStateOf(foodItem.cal.toString()) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Food EditText
        OutlinedTextField(
            value = foodText,
            onValueChange = {foodText = it},
            label = { Text("Food") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Servings EditText
        OutlinedTextField(
            value = servingsText,
            onValueChange = {servingsText = it},
            label = { Text("Servings") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Grams EditText
        OutlinedTextField(
            value = gramsText,
            onValueChange = {gramsText = it},
            label = { Text("Grams") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Price EditText
        OutlinedTextField(
            value = priceText,
            onValueChange = {priceText = it},
            label = { Text("Price") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Calories EditText
        OutlinedTextField(
            value = caloriesText,
            onValueChange = {caloriesText = it},
            label = { Text("Calories") }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Add spacing before buttons

        // Save Button
        Button(
            onClick = {
                // Convert text fields to appropriate types and create updated food item
                val updatedItem = foodItem.copy(
                    foodSource = foodText,
                    servings = servingsText.toDoubleOrNull() ?: 0.0,
                    grams = gramsText.toDoubleOrNull() ?: 0.0,
                    price = priceText.toDoubleOrNull() ?: 0.0,
                    cal = caloriesText.toDoubleOrNull() ?: 0.0
                )

                // Call the onSave callback with the updated item
                onSave(updatedItem)
                onDismiss()
                Toast.makeText(context, "Saved ${updatedItem.foodSource}", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing before dismiss button

        // Cancel Button
        Button(
            onClick = onDismiss
        ) {
            Text("Cancel")
        }
    }
}
