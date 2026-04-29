package com.example.energyapp.data

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

object QuizData {
    val questions = listOf(
        Question(
            "Which appliance typically consumes the most energy in a hostel room?",
            listOf("LED Bulb", "Electric Kettle", "Laptop Charger", "Ceiling Fan"),
            1
        ),
        Question(
            "What is the ideal temperature for an AC to save energy?",
            listOf("18°C", "21°C", "24°C", "16°C"),
            2
        ),
        Question(
            "Does leaving a charger plugged in without a phone consume power?",
            listOf("Yes", "No"),
            0
        )
    )

    fun getBadge(score: Int): String {
        return when {
            score >= 3 -> "Green Champion 🏆"
            score == 2 -> "Energy Saver ⚡"
            else -> "Beginner 🌱"
        }
    }
}