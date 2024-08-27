import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ProNutriLog.proteinCalculator.ProteinCostData
import com.ProNutriLog.proteinCalculator.ProteinCostViewModel
import java.util.UUID


@Composable
fun HomeScreen() {

    // Create or get the SharedViewModel instance
    val viewModel: ProteinCostViewModel = viewModel()
    val context = LocalContext.current

    // Define state for each text field
    var foodText by remember { mutableStateOf("") }
    var servingsText by remember { mutableStateOf("") }
    var gramsText by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var caloriesText by remember { mutableStateOf("") }
    var costPer50 by remember { mutableStateOf("50g: ") }
    var unitCost by remember { mutableStateOf("1g: ") }

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
            val item = ProteinCostData(
                id = UUID.randomUUID().toString(),
                foodSource = foodText,
                servings = servings,
                grams = grams,
                price = price,
                calories = calories,
                isSelected = false,
            )

            viewModel.updateFoodItem(context, item, false)
            Toast.makeText(context, "Saved ${item.foodSource}", Toast.LENGTH_SHORT).show()
        }
    }



    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Create references for the UI elements
        val (foodDisplay, servingsDisplay, gramsDisplay, priceDisplay, caloriesDisplay, costPer50Display, unitCostDisplay, saveButton, header, clearButton) = createRefs()

        // Define constraints
        val topGuideline = createGuidelineFromTop(0.1f)

        //Header
        Text(
            text = "ProNutriLog",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

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

        // Save Button
        Button(
            onClick = { saveItem() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            modifier = Modifier.constrainAs(saveButton) {
                top.linkTo(caloriesDisplay.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(clearButton.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Save")
        }

        // Clear Button
        Button(
            onClick = { clearFields() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier.constrainAs(clearButton) {
                top.linkTo(caloriesDisplay.bottom, margin = 16.dp)
                start.linkTo(saveButton.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Clear")
            Color(0xFF000000)
        }
    }


}

