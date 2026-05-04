package com.example.energyapp.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.energyapp.data.AboutData

@Composable
fun ImpactScreen() {
    val context = LocalContext.current

    // Pledge Counter State
    var pledgeCount by remember { mutableStateOf(342) }
    var hasPledged by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Environmental Impact Statistics
        item {
            Text("🌍 Global Impact", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // TEXT-ONLY STAT CARD
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "📈 +47%",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Projected increase in global energy demand by 2050",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        item {
            Text("Did you know?", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            AboutData.impactStats.forEach { stat ->
                Text("• $stat", modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp), fontSize = 14.sp)
            }
        }

        // 2. University Sustainability Pledge Section
        item {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text("🌱 Sustainability Pledge", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                "Join our university in pledging to reduce daily energy consumption by at least 10%. Every action counts!",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 14.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = {
                        if (!hasPledged) {
                            pledgeCount++
                            hasPledged = true
                        }
                    },
                    enabled = !hasPledged
                ) {
                    Text(if (hasPledged) "Pledged!" else "Take the Pledge")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Total Pledges: $pledgeCount", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
            }
        }

        // 3. Share Feature using Android Intent
        item {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = { shareApp(context) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Share Awareness Message")
            }
        }

        // 4. About the App (REPLACED "ABOUT US")
        item {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text("📱 About the App", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = AboutData.appName, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.primary)
                    Text(text = AboutData.appVersion, color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AboutData.appDescription,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp)) // Bottom padding for navigation bar clearance
        }
    }
}

// Function to handle the Android Share Intent
private fun shareApp(context: Context) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "🌍 I just took the pledge to save energy with our University's Energy Conservation App! Join me and let's reduce our carbon footprint together. #GoGreen #EnergySaver"
        )
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Awareness Via"))
}