import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.proNutriLog.proteinCalculator.data.model.ProteinCostData
import com.proNutriLog.proteinCalculator.viewmodel.KrogerViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(viewModel: KrogerViewModel = hiltViewModel()) {
    var searchText by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<ProteinCostData>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    val response by viewModel.products.observeAsState()

    // Update searchResults when response changes
    LaunchedEffect(response) {
        response?.let {
            searchResults = it.data.map { product ->
                ProteinCostData(
                    foodSource = product.description ?: "Unknown",
                    price = product.items?.firstOrNull()?.price?.regular ?: 0.0,
                )
            }
        }
    }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = {
                    coroutineScope.launch {
                        val results = viewModel.search(searchText, "01400943")
                        //Print the results
                        println(results)
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
                        onDelete = {},
                        onEdit = {}
                    )
                }
            }
        }
    }
}



