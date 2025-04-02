package com.proNutriLog.proteinCalculator

import AppNavHost
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.proNutriLog.proteinCalculator.ui.themes.MyTheme

class MainActivity : ComponentActivity() {

    private val TAG: String = "MainActivity"

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

@Composable
fun MainScreen() {
    // Create NavController at the top level
    val navController = rememberNavController()

    // Now pass it to AppNavHost
    AppNavHost(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}
