import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proNutriLog.proteinCalculator.data.model.User
import com.proNutriLog.proteinCalculator.ui.themes.MyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel(),
    navController: NavController
) {

    val userProfile by profileViewModel.userProfileLiveData.observeAsState(User())
    var isEditDialogVisible by remember { mutableStateOf(false) }

    // State to store the theme preference (light or dark)
    var isDarkMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.loadUserProfile()
    }

    val handleEdit: () -> Unit = {
        isEditDialogVisible = true
    }
    val coroutineScope = rememberCoroutineScope()


    // Use MyTheme composable to apply the theme
    MyTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profile") },
                    actions = {
                        IconButton(onClick = handleEdit) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Profile")
                        }
                        // Add a switch to toggle dark mode
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            modifier = Modifier.padding(16.dp)
                        )
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

                // Sign Out Button
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        authViewModel.signOut()
                        navController.navigate("login") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true // Prevents multiple copies of login on back stack
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sign Out")
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
                            profileViewModel.updateUserProfile(updatedProfile)
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
}

@Composable
fun ProfileInfo(userProfile: User) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Name: ${userProfile.first_name} ${userProfile.last_name}")
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
    onSave: (User) -> Unit,
) {
    var name by remember { mutableStateOf(userProfile.first_name + " " + userProfile.last_name) }
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
            Button(onClick = { onSave(User()) }) {
                // TODO: Implement save logic
                Text("Save")
            }
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    }

}
