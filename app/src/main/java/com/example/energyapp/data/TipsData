package com.example.energyapp.data

// ─────────────────────────────────────────────
//  Data models
// ─────────────────────────────────────────────

enum class TipCategory(val label: String, val emoji: String) {
    ALL("All", "⚡"),
    HOSTEL("Hostel", "🏠"),
    CLASSROOM("Classroom", "📚"),
    TRANSPORT("Transport", "🚌"),
    ELECTRONICS("Electronics", "📱")
}

data class EnergyTip(
    val id: Int,
    val title: String,
    val description: String,
    val category: TipCategory,
    val difficulty: String = "Easy",         // Easy / Medium / Hard
    val savingsEstimate: String = "",        // e.g. "Saves ~8W"
    val isTipOfTheWeek: Boolean = false
)

// ─────────────────────────────────────────────
//  Static tip data — add / edit freely
// ─────────────────────────────────────────────

object TipsData {

    val allTips: List<EnergyTip> = listOf(

        /* ── Hostel ── */
        EnergyTip(
            id = 1,
            title = "Turn off lights when leaving the room",
            description = "A single 8 W LED left on for 8 hours wastes 64 Wh per day. " +
                    "Make it a habit — flip the switch every time you step out, even for a few minutes.",
            category = TipCategory.HOSTEL,
            difficulty = "Easy",
            savingsEstimate = "Saves ~8 W",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 2,
            title = "Use fans instead of AC when possible",
            description = "A ceiling fan uses roughly 75 W compared to 1500 W for a window AC. " +
                    "On mild days, open windows + fan can keep you just as comfortable.",
            category = TipCategory.HOSTEL,
            difficulty = "Easy",
            savingsEstimate = "Saves up to 1.4 kW",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 3,
            title = "Set AC to 24 °C or higher",
            description = "Every 1 °C reduction in set-point increases energy consumption by ~6 %. " +
                    "24 °C is the BEE-recommended temperature for Indian climates.",
            category = TipCategory.HOSTEL,
            difficulty = "Easy",
            savingsEstimate = "Saves ~6 % per °C",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 4,
            title = "Wash clothes in cold water",
            description = "90 % of a washing machine's energy goes into heating water. " +
                    "Cold-water detergents work just as well for everyday laundry.",
            category = TipCategory.HOSTEL,
            difficulty = "Easy",
            savingsEstimate = "Saves ~0.5 kWh/wash",
            isTipOfTheWeek = false
        ),

        /* ── Classroom ── */
        EnergyTip(
            id = 5,
            title = "Use natural light during the day",
            description = "Open blinds and sit near windows before switching on tube lights. " +
                    "Daylight harvesting can cut classroom lighting energy by 20–40 %.",
            category = TipCategory.CLASSROOM,
            difficulty = "Easy",
            savingsEstimate = "Saves 20–40 %",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 6,
            title = "Switch off projectors when not in use",
            description = "Projectors consume 150–300 W. Leaving one on for an extra hour " +
                    "every day wastes 1–2 kWh per week per classroom.",
            category = TipCategory.CLASSROOM,
            difficulty = "Easy",
            savingsEstimate = "Saves 1–2 kWh/week",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 7,
            title = "Turn off computers at the end of class",
            description = "Hibernate/sleep mode still draws 1–6 W. Full shutdown saves that and " +
                    "extends hardware life. Remind the last person out to power off.",
            category = TipCategory.CLASSROOM,
            difficulty = "Easy",
            savingsEstimate = "Saves ~6 W per PC",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 8,
            title = "Report faulty fluorescent lights",
            description = "A flickering tube light can draw 2× its rated power. Report it to " +
                    "the facilities team — a ₹50 starter replacement fixes it.",
            category = TipCategory.CLASSROOM,
            difficulty = "Medium",
            savingsEstimate = "Varies",
            isTipOfTheWeek = false
        ),

        /* ── Transport ── */
        EnergyTip(
            id = 9,
            title = "Walk for distances under 1 km on campus",
            description = "Walking is zero-emission and free. Most campus buildings are within " +
                    "a 10-minute walk of each other — skip the e-rickshaw for short trips.",
            category = TipCategory.TRANSPORT,
            difficulty = "Easy",
            savingsEstimate = "0 emissions",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 10,
            title = "Carpool or use the campus shuttle",
            description = "Sharing a ride with 3 others cuts per-person fuel consumption by 75 %. " +
                    "Check the shuttle timetable in the student portal.",
            category = TipCategory.TRANSPORT,
            difficulty = "Easy",
            savingsEstimate = "Saves 75 % fuel",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 11,
            title = "Avoid idling your vehicle",
            description = "Idling for more than 10 seconds uses more fuel than restarting the engine. " +
                    "Switch off while waiting outside hostels or canteen queues.",
            category = TipCategory.TRANSPORT,
            difficulty = "Easy",
            savingsEstimate = "Saves ~0.3 L/hr",
            isTipOfTheWeek = false
        ),

        /* ── Electronics ── */
        EnergyTip(
            id = 12,
            title = "Unplug chargers when not in use",
            description = "Phone and laptop chargers draw 0.1–0.5 W even when idle (phantom load). " +
                    "Over a year, that adds up to 1–4 kWh per charger.",
            category = TipCategory.ELECTRONICS,
            difficulty = "Easy",
            savingsEstimate = "Saves 1–4 kWh/yr",
            isTipOfTheWeek = true          // ← Tip of the Week
        ),
        EnergyTip(
            id = 13,
            title = "Enable battery saver / low power mode",
            description = "Low power mode dims the screen, reduces background refresh and CPU speed, " +
                    "cutting phone energy use by up to 30 % — and extending your battery life.",
            category = TipCategory.ELECTRONICS,
            difficulty = "Easy",
            savingsEstimate = "Saves ~30 %",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 14,
            title = "Lower screen brightness",
            description = "The display is the biggest battery drain on most phones and laptops. " +
                    "Reducing brightness from 100 % to 50 % can nearly halve display power draw.",
            category = TipCategory.ELECTRONICS,
            difficulty = "Easy",
            savingsEstimate = "Saves up to 50 % display",
            isTipOfTheWeek = false
        ),
        EnergyTip(
            id = 15,
            title = "Use dark mode on OLED screens",
            description = "OLED pixels turn off for true black, so dark mode can reduce screen " +
                    "energy use by 20–60 % at high brightness settings.",
            category = TipCategory.ELECTRONICS,
            difficulty = "Easy",
            savingsEstimate = "Saves 20–60 % (OLED)",
            isTipOfTheWeek = false
        )
    )

    /** Convenience: the single tip flagged as Tip of the Week */
    val tipOfTheWeek: EnergyTip?
        get() = allTips.firstOrNull { it.isTipOfTheWeek }

    /** Filter by category (ALL returns everything) */
    fun byCategory(category: TipCategory): List<EnergyTip> =
        if (category == TipCategory.ALL) allTips
        else allTips.filter { it.category == category }

    /** Case-insensitive search across title + description */
    fun search(query: String): List<EnergyTip> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return allTips
        return allTips.filter {
            it.title.lowercase().contains(q) || it.description.lowercase().contains(q)
        }
    }
}