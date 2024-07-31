import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun HomeLayout() {
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            text = "50g:",
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
            text = "1g:",
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
            onClick = {},
            modifier = Modifier.constrainAs(saveButton) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Save")
        }

        // Calculate Button
        Button(
            onClick = {},
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
            onClick = {},
            modifier = Modifier.constrainAs(clearButton) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                end.linkTo(parent.end)
            }
        ) {
            Text("Clear")
        }
    }
}
