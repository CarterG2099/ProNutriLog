import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun EditItem() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Food EditText
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Food") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Servings EditText
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Servings") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Grams EditText
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Grams") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Price EditText
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Price") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between items

        // Calories EditText
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Calories") }
        )

        Spacer(modifier = Modifier.weight(1f)) // Push buttons to the bottom

        // Save Button
        Button(onClick = {}) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between buttons

        // Cancel Button
        Button(onClick = {}) {
            Text("Cancel")
        }
    }
}
