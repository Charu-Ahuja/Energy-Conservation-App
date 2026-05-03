package com.example.energyapp.data

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

object QuizData {
    val questions = listOf(
        Question(
            "Which of these devices can waste the most electricity if left powered on overnight unnecessarily?",
            listOf("Mobile Charger", "Desktop Computer", "LED Night Lamp", "Electric Toothbrush"),
            1,
            "Large devices in standby or 'on' mode waste much more than small chargers."
        ),
        Question(
            "What AC temperature usually balances comfort and lower electricity bills?",
            listOf("16°C", "20°C", "24°C", "27°C"),
            2,
            "24°C is the widely recommended 'Sweet Spot' for comfort and efficiency."
        ),
        Question(
            "Does 'Dark Mode' help save battery on some devices?",
            listOf("Yes, especially on OLED screens", "No, only for style", "Only while charging", "Only on laptops"),
            0,
            "OLED screens physically turn off black pixels, which saves significant power."
        ),
        Question(
            "Which usually boils water faster and more efficiently for one cup?",
            listOf("Gas Stove", "Electric Kettle", "Microwave", "Room Heater"),
            1,
            "Electric kettles are specifically designed for direct and efficient water heating."
        ),
        Question(
            "Before leaving an empty classroom, what is the best action?",
            listOf("Keep fans on", "Turn off lights and fans", "Open windows only", "Leave projector on"),
            1,
            "Simple behavioral habits are the easiest way to save energy daily."
        ),
        Question(
            "What is the most eco-friendly clothes dryer?",
            listOf("Tumble Dryer", "Hair Dryer", "Natural Sunlight", "Heater"),
            2,
            "Sunlight is free, renewable, and uses zero electrical units."
        ),
        Question(
            "A 5-star AC is generally more efficient than a 3-star AC by approximately:",
            listOf("5%", "20–25%", "50%", "No difference"),
            1,
            "Higher star ratings indicate better energy-to-cooling efficiency."
        ),
        Question(
            "If 1,000 students each save a little electricity daily, the result is:",
            listOf("No real change", "Major campus-wide savings", "Slower Wi-Fi", "More homework"),
            1,
            "Small actions multiplied by many people create a massive environmental impact."
        )
    )

    fun getBadge(score: Int): String {
        return when {
            score >= 7 -> "Eco Legend 👑"
            score >= 5 -> "Green Champion 🏆"
            score >= 3 -> "Energy Saver ⚡"
            else -> "Eco Learner 🌱"
        }
    }
}