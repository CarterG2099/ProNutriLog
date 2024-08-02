import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.ui.tooling.preview.Preview
import com.example.pc2.SharedViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FoodsScreen(viewModel: SharedViewModel = viewModel()) {

    val proteinCostList by viewModel.proteinCostLiveData.observeAsState(emptyList())

    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<ProteinCostData?>(null) }
    var isEditDialogVisible by remember { mutableStateOf(false) }
    var searchText by remember {mutableStateOf("")}

    LaunchedEffect(Unit) {
        viewModel.loadSavedData(context)
    }

    val handleDelete: (ProteinCostData) -> Unit = { itemToDelete ->
        // Create a mutable copy of the list and remove the item
        val updatedList = proteinCostList.toMutableList()
        Log.d("FoodsScreen", "Item to delete: $itemToDelete")
        updatedList.remove(itemToDelete)

        // Save the updated list back to SharedPreferences
        viewModel.updateFoodItem(context, itemToDelete, false)
    }

    val handleEdit: (ProteinCostData) -> Unit = { itemToEdit ->
        selectedItem = itemToEdit
        isEditDialogVisible = true
    }

    val handleSave: (ProteinCostData) -> Unit = { updatedItem ->
        // Update SharedPreferences with the new data
        viewModel.updateFoodItem(context, updatedItem, true)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for the UI elements
        val (searchBar, foodButton, costPer50Button, costPer1Button, servingCostButton, servingsButton, gramsButton, priceButton, calPercentButton, recyclerView) = createRefs()

        // Define constraints
        val topGuideline = createGuidelineFromTop(0.1f)

        // Search Bar
        SearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it},
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = { Text("Search") },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        searchText = ""
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        ) {
            Text("Search")
        }

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

        // LazyColumn to display the list of ProteinCostData items
        LazyColumn(
            modifier = Modifier
                .constrainAs(recyclerView) {
                    top.linkTo(foodButton.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
        ) {
            items(proteinCostList) { item ->
                ProteinCostItem(foodItem = item, onDelete = handleDelete, onEdit = handleEdit)
            }
        }
    }

    // Show EditScreen as a dialog
    selectedItem?.let { item ->
        if (isEditDialogVisible) {
            AlertDialog(
                onDismissRequest = { isEditDialogVisible = false },
                title = { Text("Edit ${item.foodSource}") },
                text = {
                    EditScreen(
                        foodItem = item,
                        onDismiss = { isEditDialogVisible = false },
                        onSave = { updatedItem ->
                            handleSave(updatedItem)
                            isEditDialogVisible = false
                    }
                )
                },
                confirmButton = {
                    Button(onClick = { isEditDialogVisible = false }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { isEditDialogVisible = false }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}
