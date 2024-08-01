import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.abs
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun ProteinCostCard(
    foodItem: ProteinCostData,
    onDelete: (ProteinCostData) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX)
    val threshold = 150f // threshold for a successful swipe

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (offsetX < -threshold) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(Color.Red) // Optional: background color for the delete icon
                    .padding(16.dp)
            )
        }

        Card(
            modifier = Modifier
                .offset { IntOffset(x = animatedOffsetX.toInt(), y = 0) } // Convert to IntOffset
                .fillMaxWidth()
                .padding(8.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            // Reset offset if not beyond threshold
                            if (abs(offsetX) <= threshold) {
                                offsetX = 0f
                            }
                        },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount
                        }
                    )
                },
            elevation = CardDefaults.cardElevation(4.dp)
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

        // Show delete icon if swipe offset is beyond threshold
        if (offsetX < -threshold) {
            IconButton(
                onClick = { onDelete(foodItem) },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(Color.Red) // Optional: background color for visibility
                    .padding(16.dp)
                    .offset { IntOffset(x = -animatedOffsetX.toInt(), y = 0) } // Align with card
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ProteinCostList(foodItems: List<ProteinCostData>, onDelete: (ProteinCostData) -> Unit) {
    LazyColumn {
        items(foodItems) { item ->
            ProteinCostCard(foodItem = item, onDelete = onDelete)
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

    var items by remember { mutableStateOf(sampleFoodItems) }

    ProteinCostList(
        foodItems = items,
        onDelete = { item ->
            items = items.filterNot { it == item }
        }
    )
}
