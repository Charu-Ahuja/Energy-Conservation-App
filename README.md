# Energy Saver 🌱
**Your smart companion for a greener, more sustainable lifestyle.**

## 📝 Project Overview
Energy Saver is an Android application designed to empower users to reduce their carbon footprint through gamified learning and actionable insights. Our mission is to help users save money and protect the planet through an interactive and rewarding journey.

## 🚀 Key Features
*   **Dashboard**:A high-performance main landing page with a modern UI and easy access to application logout.
*   **Eco-Quiz Module**: An interactive, card-based quiz experience that tests sustainability knowledge with a modern UI.
*   **Energy Calculator**: Precision tools to identify power-hungry appliances and estimate potential savings.
*   **Impact Tracker**: Visualizes the real-world difference made by the user's conservation efforts.
*   **Tips Hub**: A repository of easy-to-follow, energy-efficient habits categorized for daily routine integration.

## 🛠️ Technical Stack
*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Modern Declarative UI)
*   **Architecture**: Layered Design Pattern (Separating logic into `data` and `screens` packages)
*   **State Management**: Reactive UI updates using `mutableStateOf` and `remember`
*   **Design System**: Fully integrated with a custom UI Theme using shared color tokens like `PrimaryGreen` and `EcoBackground`.

## 📂 Project Structure
To ensure high maintainability and scalability, we followed a clean, modular package structure:
*   `com.example.energyapp.data`: Houses business logic, data models, and achievement logic (e.g., `QuizData.kt`).
*   `com.example.energyapp.screens`: Contains all UI components and screen layouts (e.g., `QuizScreen.kt`).
*   `com.example.energyapp.ui.theme`: Centralized styling and centralized design tokens.
*   `com.example.energyapp.navigation`: Manages the app's navigation flow and `NavGraph`.

## 🏁 Getting Started
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/Charu-Ahuja/Energy-Conservation-App.git
    ```
2.  **Open in Android Studio**: Use the latest version for full Jetpack Compose support.
3.  **Build and Run**: Connect your device or emulator to start your green journey!

---
*“Every small action you take today can make a big difference for our planet. Let’s save energy, save money, and protect our future — together.”**
