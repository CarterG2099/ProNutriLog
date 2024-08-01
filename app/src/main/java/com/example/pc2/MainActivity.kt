package com.example.pc2

import FoodsScreen
import HomeScreen
import MealsScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc2.ui.theme.MyTheme

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainScreen()
            }
        }
        Log.d(TAG, "onCreate() started")
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("foods") { FoodsScreen() }
            composable("meals") { MealsScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false, // Update this with actual selected state
            onClick = {
                navController.navigate("home")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Foods") },
            label = { Text("Foods") },
            selected = false, // Update this with actual selected state
            onClick = {
                navController.navigate("foods")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Meals") },
            label = { Text("Meals") },
            selected = false, // Update this with actual selected state
            onClick = {
                navController.navigate("meals")
            }
        )
    }
}



//@Composable
//fun HomeScreen() {
//    // Define the UI for the Home screen
//    Text(text = "Home Screen")
//}
//
//@Composable
//fun FoodsScreen() {
//    // Define the UI for the Foods screen
//    Text(text = "Foods Screen")
//}
//
//@Composable
//fun MealsScreen() {
//    // Define the UI for the Meals screen
//    Text(text = "Meals Screen")
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}








//package com.example.pc2
//
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.example.pc2.databinding.ActivityMainBinding
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//    private val TAG = "MainActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        Log.d(TAG, "onCreate() started")
//
//        val navView: BottomNavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_foods, R.id.navigation_meals
//            )
//        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//}
