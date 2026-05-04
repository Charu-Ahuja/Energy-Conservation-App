package com.example.energyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.energyapp.navigation.NavGraph
import com.example.energyapp.ui.theme.EnergyAppTheme
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnergyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyApp(){
    val navController = rememberNavController()
    EnergyAppTheme{
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text ("Energy Saver \uD83C\uDF31")
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Dashboard,
        Screen.Calculator,
        Screen.Tips,
        Screen.Impact,
        Screen.Quiz
    )
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Dashboard.route)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(screen.icon, contentDescription = screen.title)
                }
            )
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {

    object Dashboard : Screen("dashboard", "Home", Icons.Default.Home)
    object Tips : Screen("tips", "Tips", Icons.Default.Lightbulb)
    object Calculator : Screen("calculator", "Calculator", Icons.Default.BarChart)
    object Impact : Screen("impact", "Impact", Icons.Default.Settings)
    object Quiz : Screen("quiz", "Quiz", Icons.Default.Person)
}
