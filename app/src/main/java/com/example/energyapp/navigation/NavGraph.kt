package com.example.energyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.energyapp.Screen
import com.example.energyapp.screens.*

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }

        composable(Screen.Calculator.route) {
            CalculatorScreen(navController = navController)
        }

        composable(Screen.Tips.route) {
            TipsScreen(navController = navController)
        }

        composable(Screen.Impact.route) {
            ImpactScreen()
        }

        composable(Screen.Quiz.route) {
            QuizScreen()
        }
    }
}
