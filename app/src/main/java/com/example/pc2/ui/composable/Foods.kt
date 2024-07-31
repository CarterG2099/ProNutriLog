import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun Foods() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for the UI elements
        val (foodButton, costPer50Button, costPer1Button, servingCostButton, servingsButton, gramsButton, priceButton, calPercentButton, recyclerView, deleteSelectedButton, editButton, addFoodButton) = createRefs()

        // Define constraints
        val topGuideline = createGuidelineFromTop(0.1f)

        // Food Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(foodButton) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Food")
        }

        // Cost Per 50 Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(costPer50Button) {
                top.linkTo(topGuideline)
                start.linkTo(foodButton.end, margin = 5.dp)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("50g")
        }

        // Cost Per 1 Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(costPer1Button) {
                top.linkTo(topGuideline)
                start.linkTo(costPer50Button.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("1g")
        }

        // Serving Cost Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(servingCostButton) {
                top.linkTo(topGuideline)
                start.linkTo(costPer1Button.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Svg $")
        }

        // Servings Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(servingsButton) {
                top.linkTo(topGuideline)
                start.linkTo(servingCostButton.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("#")
        }

        // Grams Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(gramsButton) {
                top.linkTo(topGuideline)
                start.linkTo(servingsButton.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("g")
        }

        // Price Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(priceButton) {
                top.linkTo(topGuideline)
                start.linkTo(gramsButton.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Price")
        }

        // Cal Percent Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(calPercentButton) {
                top.linkTo(topGuideline)
                start.linkTo(priceButton.end)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("%")
        }

        // RecyclerView (use LazyColumn or LazyRow in Compose)
        LazyColumn(
            modifier = Modifier.constrainAs(recyclerView) {
                top.linkTo(foodButton.bottom, margin = 16.dp)
                bottom.linkTo(deleteSelectedButton.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            items(30) { index -> // Dummy data for illustration
                Text("Item $index")
            }
        }

        // Delete Selected Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(deleteSelectedButton) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        ) {
            Text("Delete")
        }

        // Edit Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(editButton) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Text("Edit")
        }

        // Add Food Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(addFoodButton) {
                bottom.linkTo(parent.bottom)
                end.linkTo(deleteSelectedButton.start, margin = 8.dp)
                start.linkTo(editButton.end, margin = 8.dp)
            }
        ) {
            Text("Add +")
        }
    }
}
