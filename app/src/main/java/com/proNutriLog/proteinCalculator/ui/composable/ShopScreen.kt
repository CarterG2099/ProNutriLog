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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.proNutriLog.proteinCalculator.data.model.ProteinCostData
import com.proNutriLog.proteinCalculator.viewmodel.ProteinCostViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(viewModel: ProteinCostViewModel = viewModel()) {
    var searchText by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<ProteinCostData>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = {
                    coroutineScope.launch {
                        val results = viewModel.searchKroger(searchText)
                        searchResults = results
                    }
                },
                active = false,
                onActiveChange = {},
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            modifier = Modifier.clickable { searchText = "" }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {}
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            LazyColumn {
                items(searchResults) { item ->
                    ProteinCostItem(
                        foodItem = item,
                        onDelete = {}, // if needed
                        onEdit = {} // if needed
                    )
                }
            }
        }
    }
}


