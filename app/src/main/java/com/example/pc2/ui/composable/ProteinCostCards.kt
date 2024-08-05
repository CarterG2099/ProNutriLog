import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProteinCostItem(
    foodItem: ProteinCostData,
    modifier: Modifier = Modifier,
    onDelete: (ProteinCostData) -> Unit,
    onEdit: (ProteinCostData) -> Unit
) {
    val TAG = "ProteinCostCard"
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(foodItem)

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                StartToEnd -> {
                    onDelete(currentItem)
                    Toast.makeText(context, "Deleted ${currentItem.foodSource}", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Deleted item: $currentItem")
                    true
                }
                EndToStart -> {
                    onEdit(currentItem)
                    true
                }
                Settled -> {
                    false
                }
            }
            return@rememberSwipeToDismissBoxState false
        },
        positionalThreshold = { it * .75f }
    )
    Log.d(TAG, "SwipeToDismissBox called: $currentItem")

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        backgroundContent = { DismissBackground(dismissState) },
        content = {
            ProteinCostCard(currentItem)
        })
}

@Composable
fun ProteinCostCard(foodItem: ProteinCostData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                        Text("Cals/Serving: ${foodItem.caloriesPerServing}", fontSize = 14.sp)
                        Text("Cal%: ${foodItem.percentCalFromProtein}")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Cost/50g: ${foodItem.costPer50}", fontSize = 14.sp)
                        Text("Cost/g: ${foodItem.costPerGram}", fontSize = 14.sp)
                        Text("Cost/Serving: ${foodItem.servingCost}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProteinCostList() {
    // Provide a list of sample data for the preview
    val sampleFoodItems = listOf(
        ProteinCostData(
            id = "1",
            foodSource = "Chicken Breast",
            servings = 200.0,
            grams = 150.0,
            price = 5.99,
            calories = 80.0
        ),
        ProteinCostData(
            id = "2",
            foodSource = "Salmon",
            servings = 150.0,
            grams = 200.0,
            price = 8.99,
            calories = 70.0
        )
    )

    var items by remember { mutableStateOf(sampleFoodItems) }


    // LazyColumn to display the list of ProteinCostData items
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(items) { item ->
            val handleDelete: (ProteinCostData) -> Unit = { /* Handle delete action */ }
            ProteinCostItem(foodItem = item, onDelete = handleDelete, onEdit = {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color(0xFFFF1744)
        SwipeToDismissBoxValue.EndToStart -> Color(0xFF1DE9B6)
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "Delete"
        )
        Spacer(modifier = Modifier)
        Icon(
            painter = painterResource(id = R.drawable.baseline_archive_24),
            contentDescription = "Edi"
        )
    }
}

