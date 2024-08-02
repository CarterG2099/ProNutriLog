import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc2.SharedViewModel
import java.util.UUID


@Composable
fun HomeScreen() {

    // Create or get the SharedViewModel instance
    val viewModel: SharedViewModel = viewModel()
    val context = LocalContext.current


    // Define state for each text field
    var foodText by remember { mutableStateOf("") }
    var servingsText by remember { mutableStateOf("") }
    var gramsText by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var caloriesText by remember { mutableStateOf("") }
    var costPer50 by remember { mutableStateOf("50g: ") }
    var unitCost by remember { mutableStateOf("1g: ") }
    var proteinCostList by remember { mutableStateOf(mutableListOf<ProteinCostData>()) }


    // Function to clear all fields
    fun clearFields() {
        foodText = ""
        servingsText = ""
        gramsText = ""
        priceText = ""
        caloriesText = ""
        costPer50 = ""
        unitCost = ""
    }

    fun saveItem() {
        val servings = servingsText.toDoubleOrNull() ?: 0.0
        val grams = gramsText.toDoubleOrNull() ?: 0.0
        val price = priceText.toDoubleOrNull() ?: 0.0
        val calories = caloriesText.toDoubleOrNull() ?: 0.0

        if (servings > 0 && grams > 0 && price > 0 && calories > 0) {
            val fiftyGramsCost = String.format("%.2f", (price / (grams * servings)) * 50)
            val oneGramCost = String.format("%.2f", (price / (grams * servings)) * 100)

            val item = ProteinCostData(
                id = UUID.randomUUID().toString(),
                foodSource = foodText,
                servings = servings,
                grams = grams,
                price = price,
                fiftyGrams = fiftyGramsCost,
                oneGram = oneGramCost,
                cal = calories,
                calories = String.format("%.2f", ((grams * 4) / calories) * 100),
                isSelected = false,
            )

//            viewModel.loadSavedData()
//            proteinCostList.add(item)
//            viewModel.updateProteinCostList(foodItems)
//            viewModel.saveFoodItems(context, foodItems)
//            viewModel.updateDataToSharedPreferences(context, foodItems)

            viewModel.updateFoodItem(context, item, false)
            Toast.makeText(context, "Saved ${item.foodSource}", Toast.LENGTH_SHORT).show()
//            clearFields()
        }
    }

    fun calculate() {
        val servings = servingsText.toDoubleOrNull() ?: 0.0
        val grams = gramsText.toDoubleOrNull() ?: 0.0
        val price = priceText.toDoubleOrNull() ?: 0.0
        val calories = caloriesText.toDoubleOrNull() ?: 0.0

        // Check for invalid input
        if (servings == 0.0 || grams == 0.0 || price == 0.0 || calories == 0.0) {
            costPer50 = ""
            unitCost = "Please Provide Valid Information"
            return
        }

        // Perform calculations
        val unitCostValue = (price / (grams * servings)) * 100
        val costPer50Value = (price / (grams * servings)) * 50

        // Format results
        costPer50 = "50g: $" + String.format("%.2f", costPer50Value)
        unitCost = "1g: " + String.format("%.2f", unitCostValue) + " Cents"
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for the UI elements
        val (foodDisplay, servingsDisplay, gramsDisplay, priceDisplay, caloriesDisplay, costPer50Display, unitCostDisplay, saveButton, calcButton, clearButton) = createRefs()

        // Define constraints
        val topGuideline = createGuidelineFromTop(0.1f)

        // Food EditText
        OutlinedTextField(
            value = foodText,
            onValueChange = {foodText = it},
            label = { Text("Food") },
            modifier = Modifier.constrainAs(foodDisplay) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )

        // Servings EditText
        OutlinedTextField(
            value = servingsText,
            onValueChange = {servingsText =  it},
            label = { Text("Servings") },
            modifier = Modifier.constrainAs(servingsDisplay) {
                top.linkTo(foodDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )

        // Grams EditText
        OutlinedTextField(
            value = gramsText,
            onValueChange = {gramsText = it},
            label = { Text("Grams") },
            modifier = Modifier.constrainAs(gramsDisplay) {
                top.linkTo(servingsDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )

        // Price EditText
        OutlinedTextField(
            value = priceText,
            onValueChange = {priceText = it},
            label = { Text("Price") },
            modifier = Modifier.constrainAs(priceDisplay) {
                top.linkTo(gramsDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )

        // Calories EditText
        OutlinedTextField(
            value = caloriesText,
            onValueChange = {caloriesText = it},
            label = { Text("Calories") },
            modifier = Modifier.constrainAs(caloriesDisplay) {
                top.linkTo(priceDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )

        // Cost Per 50 TextView
        Text(
            text = costPer50,
            modifier = Modifier.constrainAs(costPer50Display) {
                top.linkTo(caloriesDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            },
            style = MaterialTheme.typography.bodyLarge
        )

        // Unit Cost TextView
        Text(
            text = unitCost,
            modifier = Modifier.constrainAs(unitCostDisplay) {
                top.linkTo(costPer50Display.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            },
            style = MaterialTheme.typography.bodyLarge
        )

        // Save Button
        Button(
            onClick = { saveItem() },
            modifier = Modifier.constrainAs(saveButton) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Save")
        }

        // Calculate Button
        Button(
            onClick = { calculate() },
            modifier = Modifier.constrainAs(calcButton) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(saveButton.end, margin = 16.dp)
                end.linkTo(clearButton.start, margin = 16.dp)
            }
        ) {
            Text("Calculate")
        }

        // Clear Button
        Button(
            onClick = { clearFields() },
            modifier = Modifier.constrainAs(clearButton) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                end.linkTo(parent.end)
            }
        ) {
            Text("Clear")
        }
    }


}

