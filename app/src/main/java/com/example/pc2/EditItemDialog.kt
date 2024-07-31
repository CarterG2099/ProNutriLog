import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc2.ProteinCostAdapter
import com.example.pc2.SharedViewModel


@Composable
fun EditItemDialog(
    onDismiss: () -> Unit,
    sharedViewModel: SharedViewModel,
    proteinCostAdapter: ProteinCostAdapter,
    selectedItem: ProteinCostData
) {
    var foodSource by remember { mutableStateOf(selectedItem.foodSource) }
    var servings by remember { mutableStateOf(selectedItem.servings) }
    var grams by remember { mutableStateOf(selectedItem.grams) }
    var price by remember { mutableStateOf(selectedItem.price) }
    var calories by remember { mutableStateOf(selectedItem.calories) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 24.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Edit Item", style = MaterialTheme.typography.h6)

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = foodSource,
                    onValueChange = { foodSource = it },
                    label = { Text("Food Source") }
                )

                OutlinedTextField(
                    value = servings,
                    onValueChange = { servings = it },
                    label = { Text("Servings") }
                )

                OutlinedTextField(
                    value = grams,
                    onValueChange = { grams = it },
                    label = { Text("Grams") }
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") }
                )

                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (foodSource.isBlank() || servings.isBlank() || grams.isBlank() || price.isBlank() || calories.isBlank()) {
                            Toast.makeText(LocalContext.current, "Incomplete Information", Toast.LENGTH_SHORT).show()
                        } else {
                            // Perform calculations and save data
                            val calPercent = calcCalPercent(grams, calories)
                            val unitCost = calcUnitCost(price, grams, servings)
                            val costPer50 = calcCostPer50(price, grams, servings)
                            saveData(
                                sharedViewModel,
                                proteinCostAdapter,
                                foodSource,
                                servings,
                                grams,
                                price,
                                calPercent,
                                unitCost,
                                costPer50
                            )
                            onDismiss()
                        }
                    }) {
                        Text("Save")
                    }

                    Button(onClick = { onDismiss() }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

private fun calcCalPercent(grams: String, calories: String): Double {
    val g = grams.toDoubleOrNull() ?: 0.0
    val cal = calories.toDoubleOrNull() ?: 0.0
    return if (cal != 0.0) (((g * 4) / cal) * 100) else 0.0
}

private fun calcUnitCost(price: String, grams: String, servings: String): Double {
    val p = price.toDoubleOrNull() ?: 0.0
    val g = grams.toDoubleOrNull() ?: 0.0
    val s = servings.toDoubleOrNull() ?: 0.0
    return if (g * s != 0.0) (p / (g * s)) * 100 else 0.0
}

private fun calcCostPer50(price: String, grams: String, servings: String): Double {
    val p = price.toDoubleOrNull() ?: 0.0
    val g = grams.toDoubleOrNull() ?: 0.0
    val s = servings.toDoubleOrNull() ?: 0.0
    return if (g * s != 0.0) (p / (g * s)) * 50 else 0.0
}

@Composable
private fun saveData(
    sharedViewModel: SharedViewModel,
    proteinCostAdapter: ProteinCostAdapter,
    foodSource: String,
    servings: String,
    grams: String,
    price: String,
    calPercent: Double,
    unitCost: Double,
    costPer50: Double
) {
    // Assuming `selectedItem` is passed as an argument or accessed from ViewModel
    // Replace `selectedItem` with the correct item if needed
//    val selectedItem = ProteinCostData() // Replace with actual data
//    selectedItem.foodSource = foodSource
//    selectedItem.servings = servings
//    selectedItem.grams = grams
//    selectedItem.price = price
//    selectedItem.cal = String.format("%.0f", calPercent)
//    selectedItem.oneGram = String.format("%.2f", unitCost)
//    selectedItem.fiftyGrams = String.format("%.2f", costPer50)
//    selectedItem.servingCost = String.format("%.2f", price.toDoubleOrNull()?.div(servings.toDoubleOrNull()!!) ?: 0.0)

    // Save data to ViewModel or SharedPreferences
    sharedViewModel.replaceDataToSharedPreferences(LocalContext.current, sharedViewModel.proteinCostList)
    proteinCostAdapter.notifyDataSetChanged()
}
