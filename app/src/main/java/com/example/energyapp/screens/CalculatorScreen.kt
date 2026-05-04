package com.example.energyapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.energyapp.R
import kotlin.math.roundToInt


@Composable
fun CalculatorScreen(navController: NavController) {

    var wattageInput by remember { mutableStateOf("") }
    var hoursInput by remember { mutableStateOf("") }

    var dailyKwh by remember { mutableStateOf(0.0) }
    var monthlyKwh by remember { mutableStateOf(0.0) }
    var monthlyCost by remember { mutableStateOf(0.0) }
    var carbonEmission by remember { mutableStateOf(0.0) }
    var savingsSuggestion by remember { mutableStateOf("") }
    var ecoScore by remember { mutableStateOf(100) }
    var progressValue by remember { mutableStateOf(1f) }
    var dailyCost by remember { mutableStateOf(0.0) }
    var usageFeedback by remember { mutableStateOf("") }

    val electricityRate = 8.0   // ₹ per kWh
    val carbonFactor = 0.82     // kg CO₂ per kWh

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //  APP LOGO
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        )

        //  PAGE TITLE
        Text(
            text = "Energy Consumption Calculator",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Calculate your appliance energy usage and savings",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(25.dp))

        //  WATTAGE INPUT
        OutlinedTextField(
            value = wattageInput,
            onValueChange = { wattageInput = it },
            label = { Text("Appliance Wattage (W)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        // HOURS INPUT
        OutlinedTextField(
            value = hoursInput,
            onValueChange = { hoursInput = it },
            label = { Text("Hours Used Per Day") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        // CALCULATE BUTTON
        Button(
            onClick = {
                val wattage = wattageInput.toDoubleOrNull() ?: 0.0
                val hours = hoursInput.toDoubleOrNull() ?: 0.0

                dailyKwh = (wattage * hours) / 1000
                monthlyKwh = dailyKwh * 30
                monthlyCost = monthlyKwh * electricityRate
                carbonEmission = monthlyKwh * carbonFactor
                dailyCost = dailyKwh * electricityRate

                usageFeedback = when {
                    dailyKwh < 1 -> "Excellent usage 🌱"
                    dailyKwh < 5 -> "Moderate usage ⚡ Try reducing"
                    else -> "High usage ⚠️ Save energy"
                }

                savingsSuggestion =
                    if (hours > 2) {
                        val reducedCost = ((wattage * (hours - 2)) / 1000) * 30 * electricityRate
                        "Reduce usage by 2 hrs/day and save approximately ₹${(monthlyCost - reducedCost).roundToInt()} per month."
                    } else {
                        "Your usage is already efficient!"
                    }
                ecoScore = when {
                    dailyKwh < 2 -> 95
                    dailyKwh < 5 -> 80
                    dailyKwh < 8 -> 65
                    else -> 40
                }

                progressValue = ecoScore / 100f
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Calculate",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                wattageInput = ""
                hoursInput = ""
                dailyKwh = 0.0
                monthlyKwh = 0.0
                monthlyCost = 0.0
                carbonEmission = 0.0
                savingsSuggestion = ""
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Reset Calculator")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // RESULTS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Results",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Daily Energy Consumption: %.2f kWh".format(dailyKwh))
                Text("Monthly Energy Consumption: %.2f kWh".format(monthlyKwh))
                Text("Estimated Monthly Bill: ₹%.2f".format(monthlyCost))
                Text("Estimated Daily Cost: ₹%.2f".format(dailyCost))
                Text("Estimated Carbon Footprint: %.2f kg CO₂".format(carbonEmission))

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = savingsSuggestion,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Eco Efficiency Score: $ecoScore/100 🌿",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = usageFeedback,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )

                LinearProgressIndicator(
                    progress = { progressValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        //  NAVIGATION SECTION
        Text(
            text = "Explore More",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(15.dp))

        NavigationButton(" Dashboard") {
            navController.navigate("dashboard")
        }

        NavigationButton("Energy Tips") {
            navController.navigate("tips")
        }

        NavigationButton("Green Quiz") {
            navController.navigate("quiz")
        }

        NavigationButton("Impact & About Us") {
            navController.navigate("impact")
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun NavigationButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}