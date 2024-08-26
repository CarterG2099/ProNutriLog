import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadSavedData(context)
    }

    val handleDelete: (ProteinCostData) -> Unit = { itemToDelete ->
        val updatedList = proteinCostList.toMutableList()
        updatedList.remove(itemToDelete)
        viewModel.updateFoodItem(context, itemToDelete, false)
    }

    val handleEdit: (ProteinCostData) -> Unit = { itemToEdit ->
        selectedItem = itemToEdit
        isEditDialogVisible = true
    }

    val handleSave: (ProteinCostData) -> Unit = { updatedItem ->
        viewModel.updateFoodItem(context, updatedItem, true)
    }

    val filteredList = proteinCostList.filter {
        it.foodSource.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            // Sort List
            SortList(viewModel, modifier = Modifier.fillMaxWidth())

            // LazyColumn to display the list of ProteinCostData items
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Ensures the LazyColumn takes available space
                    .padding(8.dp)
            ) {
                items(filteredList) { item ->
                    ProteinCostItem(foodItem = item, onDelete = handleDelete, onEdit = handleEdit)
                }
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
