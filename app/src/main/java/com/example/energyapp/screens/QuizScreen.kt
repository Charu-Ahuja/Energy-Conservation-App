package com.example.energyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// These connect to your team's data and theme
import com.example.energyapp.data.QuizData
import com.example.energyapp.ui.theme.*

@Composable
fun QuizScreen() {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }
    var showExplanation by remember { mutableStateOf(false) }

    val questions = QuizData.questions
    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackground) // Use the theme's background
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Green Quiz",
            style = MaterialTheme.typography.headlineMedium,
            color = PrimaryGreen,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (!isQuizFinished) {
            // Feature: Modern Progress Bar
            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1).toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(10.dp)),
                color = SecondaryGreen,
                trackColor = Color.LightGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Feature: Question Card (This makes it look modern!)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = currentQuestion.text,
                        style = MaterialTheme.typography.titleLarge,
                        color = DarkGreenText
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Feature: Styled Option Boxes instead of plain radio buttons
                    currentQuestion.options.forEachIndexed { index, option ->
                        val isSelected = selectedOption == index
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) PrimaryGreen else Color.Transparent)
                                .border(1.dp, if (isSelected) PrimaryGreen else SecondaryGreen, RoundedCornerShape(12.dp))
                                .clickable { if (!showExplanation) selectedOption = index }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = option,
                                color = if (isSelected) Color.White else DarkGreenText
                            )
                        }
                    }
                }
            }

            if (showExplanation) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = EnergyYellow.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "💡 ${currentQuestion.explanation}",
                        modifier = Modifier.padding(16.dp),
                        color = DarkGreenText,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Feature: Big Primary Action Button
            Button(
                onClick = {
                    if (!showExplanation) {
                        if (selectedOption == currentQuestion.correctAnswerIndex) score++
                        showExplanation = true
                    } else {
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                            selectedOption = null
                            showExplanation = false
                        } else {
                            isQuizFinished = true
                        }
                    }
                },
                enabled = selectedOption != null,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(if (!showExplanation) "CHECK ANSWER" else "NEXT")
            }
        } else {
            // Results View
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(QuizData.getBadge(score), fontSize = 32.sp, color = PrimaryGreen, fontWeight = FontWeight.Bold)
                Text("Final Score: $score / ${questions.size}", color = DarkGreenText)
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {
                    isQuizFinished = false; score = 0; currentQuestionIndex = 0; selectedOption = null; showExplanation = false
                }, colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen)) {
                    Text("Restart Quiz")
                }
            }
        }
    }
}