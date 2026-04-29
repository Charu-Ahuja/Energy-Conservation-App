package com.example.energyapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.energyapp.R
import kotlin.random.Random

@Composable
fun DashboardScreen() {

    // 🌱 List of Energy Saving Tips
    val tips = listOf(
        "Turn off lights when not in use",
        "Use LED bulbs instead of CFL",
        "Unplug devices when fully charged",
        "Use natural light during the day",
        "Switch off appliances at night",
        "Use energy-efficient appliances",
        "Limit AC usage and use fans",
        "Wash clothes in cold water",
        "Keep fridge door closed properly",
        "Use power strips to reduce standby power"
    )

    // 🎯 Random Tip Generator
    val randomTip = remember {
        tips[Random.nextInt(tips.size)]
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🌿 Banner / Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF66BB6A),
                            Color(0xFF43A047)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // App Logo (Replace with your logo)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Energy Saver",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 👋 Welcome Message
        Text(
            text = "Welcome Back!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Let’s save energy and protect our planet 🌍",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🎯 Mission Section
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Our Mission",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "To promote sustainable energy usage by encouraging simple daily habits that reduce electricity consumption and protect the environment.",
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 💡 Daily Tip Section
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "💡 Today's Tip",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = randomTip,
                    fontSize = 15.sp
                )
            }
        }
    }
}