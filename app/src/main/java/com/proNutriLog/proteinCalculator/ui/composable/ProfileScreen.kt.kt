import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.proNutriLog.proteinCalculator.data.model.User
import com.proNutriLog.proteinCalculator.viewmodel.ProteinCostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {

    val userProfile by viewModel.userProfileLiveData.observeAsState(User())
    var isEditDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    val handleEdit: () -> Unit = {
        isEditDialogVisible = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                actions = {
                    IconButton(onClick = handleEdit) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Profile")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Profile details display
            ProfileInfo(userProfile)

            // Edit Profile Button
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { isEditDialogVisible = true }) {
                Text("Edit Profile")
            }
        }
    }

    // Show Edit Profile Dialog
    if (isEditDialogVisible) {
        AlertDialog(
            onDismissRequest = { isEditDialogVisible = false },
            title = { Text("Edit Profile") },
            text = {
                EditProfileScreen(
                    userProfile = userProfile,
                    onDismiss = { isEditDialogVisible = false },
                    onSave = { updatedProfile ->
                        viewModel.updateUserProfile(updatedProfile)
                        isEditDialogVisible = false
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = { isEditDialogVisible = false }) {
                    Text("Cancel")
                }
            },
            dismissButton = {
                TextButton(onClick = { isEditDialogVisible = false }) {
                    Text("Save")
                }
            }
        )
    }
}

@Composable
fun ProfileInfo(userProfile: User) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Name: ${userProfile.name}")
        Text("Email: ${userProfile.email}")
        Text("Age: ${userProfile.age}")
        Text("Height: ${userProfile.height} cm")
        Text("Weight: ${userProfile.weight} kg")
        Text("Dietary Preferences: ${userProfile.dietaryPreferences}")
    }
}

@Composable
fun EditProfileScreen(
    userProfile: User,
    onDismiss: () -> Unit,
    onSave: (User) -> Unit
) {
    var name by remember { mutableStateOf(userProfile.name) }
    var email by remember { mutableStateOf(userProfile.email) }
    var age by remember { mutableStateOf(userProfile.age.toString()) }
    var height by remember { mutableStateOf(userProfile.height.toString()) }
    var weight by remember { mutableStateOf(userProfile.weight.toString()) }
    var dietaryPreferences by remember { mutableStateOf(userProfile.dietaryPreferences) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = dietaryPreferences,
            onValueChange = { dietaryPreferences = it },
            label = { Text("Dietary Preferences") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { onSave(User(name, email, age.toInt(), height.toInt(), weight.toInt(), dietaryPreferences)) }) {
                Text("Save")
            }
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    }
}

