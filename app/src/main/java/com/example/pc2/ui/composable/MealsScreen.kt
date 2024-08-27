import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun MealsScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for the UI elements
        val (mealButton, mealPriceButton, mealProteinButton, mealProteinCostButton, mealCaloriesButton, mealPercentButton) = createRefs()

        // Meal Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealButton) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text("Meal")
        }

        // Meal Price Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealPriceButton) {
                start.linkTo(mealButton.end, margin = 5.dp)
                top.linkTo(parent.top)
                width = Dimension.value(49.dp)
                height = Dimension.wrapContent
            }
        ) {
            Text("Meal $")
        }

        // Meal Protein Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealProteinButton) {
                start.linkTo(mealPriceButton.end)
                top.linkTo(parent.top)
                width = Dimension.value(35.dp)
                height = Dimension.wrapContent
            }
        ) {
            Text("Prtn")
        }

        // Meal Protein Cost Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealProteinCostButton) {
                start.linkTo(mealProteinButton.end)
                top.linkTo(parent.top)
                width = Dimension.value(46.dp)
                height = Dimension.wrapContent
            }
        ) {
            Text("Prtn $")
        }

        // Meal Calories Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealCaloriesButton) {
                end.linkTo(mealPercentButton.start)
                top.linkTo(parent.top)
                width = Dimension.value(50.dp)
                height = Dimension.wrapContent
            }
        ) {
            Text("Cals")
        }

        // Meal Percent Button
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mealPercentButton) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                width = Dimension.value(30.dp)
                height = Dimension.wrapContent
            }
        ) {
            Text("%")
        }
    }
}
