package com.proNutriLog.proteinCalculator

import AppNavHost
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.proNutriLog.proteinCalculator.ui.themes.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme() {
                MainScreen()
            }
        }
        Log.d(TAG, "onCreate() started")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    // Create NavController at the top level
    val navController = rememberNavController()

    // Check if user is already logged in
    val currentUser = SupabaseRepository.getCurrentUser()

    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true } // Clears backstack so user can't press back to go home
            }
        } else {
            navController.navigate("home") // Navigate to home if already logged in
        }
    }

    // Now pass it to AppNavHost
    AppNavHost(navController = navController)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}
