import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proNutriLog.proteinCalculator.viewmodel.ProteinCostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortList(viewModel: ProteinCostViewModel, modifier: Modifier) {
    val options = listOf("Name", "Price", "Cals", "Grams", "Serv")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var isAscending by remember { mutableStateOf(true) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Sort By:",
            style = MaterialTheme.typography.bodyLarge
        )
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, option ->
                SegmentedButton(
                    selected = selectedOption == option,
                    onClick = {
                        if (selectedOption == option) {
                            isAscending = !isAscending
                        } else {
                            selectedOption = option
                            isAscending = true
                        }
                        viewModel.sortProteinCostList(selectedOption, isAscending)
                        viewModel.sortProteinCostList(selectedOption, isAscending)

                              },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    )
                ) {
                    Text(text = option)
                }
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SegmentedButtonExamplePreview() {
//    SortList(emptyList(), Modifier.Companion.constrainAs(sortList) {
//        top.linkTo(searchBar.Bottom, margin = 16.dp)
//        start.linkTo(parent.start)
//        end.linkTo(parent.end)
//    })
//}