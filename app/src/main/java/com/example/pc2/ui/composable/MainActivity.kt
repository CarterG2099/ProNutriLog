import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pc2.ui.theme.MyTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (navHost, bottomNav) = createRefs()

        NavHost(
            navController = navController,
            startDestination = "home", // Replace with your start destination
            Modifier.constrainAs(navHost) {
                top.linkTo(parent.top)
                bottom.linkTo(bottomNav.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            // Define your composable destinations here
            // composable("home") { HomeScreen(navController) }
            // composable("details") { DetailsScreen() }
        }

        BottomNavigation(
            modifier = Modifier.constrainAs(bottomNav) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = false, // Update selection state
                onClick = {
                    // Handle navigation
                    navController.navigate("home")
                }
            )
            // Add more items as needed
        }
    }
}
