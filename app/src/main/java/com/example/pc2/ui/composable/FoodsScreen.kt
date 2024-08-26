import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc2.ProteinCostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FoodsScreen(viewModel: ProteinCostViewModel = viewModel()) {

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

    // Filter the list based on the search query
    val filteredList = proteinCostList.filter {
        it.foodSource.contains(searchText, ignoreCase = true)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for the UI elements
        val (searchBar, sortList, recyclerView) = createRefs()

        // Define constraints
        val topGuideline = createGuidelineFromTop(0.0f)

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
            },
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                }
        ) {
            Text("Search")
        }

        // Sort List
        SortList(viewModel, modifier = Modifier.constrainAs(sortList) {
            top.linkTo(searchBar.bottom, margin = 0.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        // LazyColumn to display the list of ProteinCostData items
        LazyColumn(
            modifier = Modifier
                .constrainAs(recyclerView) {
                    top.linkTo(sortList.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
        ) {
            items(filteredList) { item ->
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
