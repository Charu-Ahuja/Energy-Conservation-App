package com.example.mad_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mad_project.data.QuizData

@Composable
fun QuizScreen() {
    val context = LocalContext.current
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    val questions = QuizData.questions

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Green Quiz", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))

        if (!quizFinished) {
            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1).toFloat() / questions.size,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))
            Text(questions[currentQuestionIndex].text)

            questions[currentQuestionIndex].options.forEachIndexed { index, option ->
                Row(
                    Modifier.fillMaxWidth().selectable(
                        selected = (selectedOption == index),
                        onClick = { selectedOption = index }
                    ).padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = (selectedOption == index), onClick = { selectedOption = index })
                    Text(option, Modifier.padding(start = 8.dp))
                }
            }

            Button(
                onClick = {
                    if (selectedOption == questions[currentQuestionIndex].correctAnswerIndex) score++
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                        selectedOption = null
                    } else {
                        quizFinished = true
                    }
                },
                modifier = Modifier.padding(top = 16.dp),
                enabled = selectedOption != null
            ) {
                Text(if (currentQuestionIndex < questions.size - 1) "Next" else "Finish")
            }
        } else {
            Text("Score: $score / ${questions.size}", style = MaterialTheme.typography.headlineSmall)
            Text("Badge: ${QuizData.getBadge(score)}")
            Button(onClick = {
                quizFinished = false; score = 0; currentQuestionIndex = 0; selectedOption = null
            }) {
                Text("Restart")
            }
        }
    }
}