package com.example.energyapp.screens

import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.energyapp.data.EnergyTip
import com.example.energyapp.data.TipCategory
import com.example.energyapp.data.TipsData

// ─────────────────────────────────────────────
//  Colour palette (matches colors.xml)
// ─────────────────────────────────────────────

private val GreenLight   = Color(0xFFEAF3DE)
private val GreenBorder  = Color(0xFF639922)
private val GreenDark    = Color(0xFF3B6D11)

private val BlueLight    = Color(0xFFE6F1FB)
private val BlueBorder   = Color(0xFF185FA5)
private val BlueDark     = Color(0xFF042C53)

private val AmberLight   = Color(0xFFFAEEDA)
private val AmberBorder  = Color(0xFFBA7517)
private val AmberDark    = Color(0xFF412402)

private val CoralLight   = Color(0xFFFAECE7)
private val CoralBorder  = Color(0xFF993C1D)
private val CoralDark    = Color(0xFF4A1B0C)

private val PurpleLight  = Color(0xFFEEEDFE)
private val PurpleBorder = Color(0xFF534AB7)
private val PurpleDark   = Color(0xFF26215C)

// Map category → (background, border, text) colours
private fun categoryColors(cat: TipCategory): Triple<Color, Color, Color> = when (cat) {
    TipCategory.HOSTEL      -> Triple(AmberLight, AmberBorder, AmberDark)
    TipCategory.CLASSROOM   -> Triple(BlueLight,  BlueBorder,  BlueDark)
    TipCategory.TRANSPORT   -> Triple(CoralLight, CoralBorder, CoralDark)
    TipCategory.ELECTRONICS -> Triple(GreenLight, GreenBorder, GreenDark)
    TipCategory.ALL         -> Triple(PurpleLight, PurpleBorder, PurpleDark)
}

// ─────────────────────────────────────────────
//  Root screen composable
// ─────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(navController: NavController) {
    var searchQuery       by remember { mutableStateOf("") }
    var selectedCategory  by remember { mutableStateOf(TipCategory.ALL) }
    val expandedTipIds    = remember { mutableStateListOf<Int>() }

    // Derive the visible list from search + category filter
    val visibleTips: List<EnergyTip> = remember(searchQuery, selectedCategory) {
        val base = if (searchQuery.isBlank()) TipsData.byCategory(selectedCategory)
        else TipsData.search(searchQuery)
        if (selectedCategory == TipCategory.ALL || searchQuery.isNotBlank()) base
        else base.filter { it.category == selectedCategory }
    }

    val tipOfTheWeek = TipsData.tipOfTheWeek

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Energy Tips",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {

            // ── Search bar ──────────────────────────────
            item {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                        if (it.isNotBlank()) selectedCategory = TipCategory.ALL
                    },
                    onClear = { searchQuery = "" }
                )
            }

            // ── Tip of the Week ─────────────────────────
            if (tipOfTheWeek != null && searchQuery.isBlank()) {
                item {
                    TipOfTheWeekCard(tip = tipOfTheWeek)
                }
            }

            // ── Category filter chips ───────────────────
            item {
                CategoryChipRow(
                    selected = selectedCategory,
                    onSelect = {
                        selectedCategory = it
                        searchQuery = ""
                    }
                )
            }

            // ── Results header ──────────────────────────
            item {
                Text(
                    text = when {
                        searchQuery.isNotBlank() ->
                            "${visibleTips.size} result${if (visibleTips.size != 1) "s" else ""} for \"$searchQuery\""
                        selectedCategory == TipCategory.ALL ->
                            "All tips (${visibleTips.size})"
                        else ->
                            "${selectedCategory.label} tips (${visibleTips.size})"
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // ── Tip cards ───────────────────────────────
            if (visibleTips.isEmpty()) {
                item { EmptyState(query = searchQuery) }
            } else {
                items(visibleTips, key = { it.id }) { tip ->
                    TipCard(
                        tip = tip,
                        isExpanded = tip.id in expandedTipIds,
                        onToggle = {
                            if (tip.id in expandedTipIds) expandedTipIds.remove(tip.id)
                            else expandedTipIds.add(tip.id)
                        }
                    )
                }
            }

            // ── Navigation Section ──────────────────────
            item {
                Text(
                    text = "Explore More",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(15.dp))
                NavigationButton("🏠 Dashboard") {
                    navController.navigate("dashboard")
                }
                NavigationButton("🧮 Calculator") {
                    navController.navigate("calculator")
                }
                NavigationButton("🟢 Green Quiz") {
                    navController.navigate("quiz")
                }
                NavigationButton("🌍 Impact & About Us") {
                    navController.navigate("impact")
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

            // Bottom padding so the last card isn't hidden by nav bar
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

// ─────────────────────────────────────────────
//  Search bar
// ─────────────────────────────────────────────

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search tips…") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Default.Close, contentDescription = "Clear search")
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}

// ─────────────────────────────────────────────
//  Tip of the Week card
// ─────────────────────────────────────────────

@Composable
private fun TipOfTheWeekCard(tip: EnergyTip) {
    val (bg, border, textColor) = categoryColors(tip.category)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, border, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("⭐", fontSize = 14.sp)
                    Text(
                        text = "Tip of the Week",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = border
                    )
                    Spacer(Modifier.weight(1f))
                    CategoryBadge(category = tip.category)
                }

                Spacer(Modifier.height(6.dp))

                Text(
                    text = tip.title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = textColor
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = tip.description,
                    fontSize = 13.sp,
                    color = textColor,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ─────────────────────────────────────────────
//  Category filter chip row
// ─────────────────────────────────────────────

@Composable
private fun CategoryChipRow(
    selected: TipCategory,
    onSelect: (TipCategory) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(TipCategory.entries) { cat ->
            CategoryChip(
                category = cat,
                isSelected = cat == selected,
                onClick = { onSelect(cat) }
            )
        }
    }
}

@Composable
private fun CategoryChip(
    category: TipCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (bg, border, textColor) = categoryColors(category)

    val chipBg   = if (isSelected) bg   else MaterialTheme.colorScheme.surface
    val chipText = if (isSelected) textColor else MaterialTheme.colorScheme.onSurfaceVariant
    val chipBorder = if (isSelected) border else MaterialTheme.colorScheme.outlineVariant

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(chipBg)
            .border(1.dp, chipBorder, CircleShape)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(category.emoji, fontSize = 13.sp)
            Text(
                text = category.label,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                color = chipText
            )
        }
    }
}

// ─────────────────────────────────────────────
//  Expandable tip card
// ─────────────────────────────────────────────

@Composable
fun TipCard(
    tip: EnergyTip,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    val (bg, border, textColor) = categoryColors(tip.category)

    val cardBg     = if (isExpanded) bg else MaterialTheme.colorScheme.surface
    val cardBorder = if (isExpanded) border else MaterialTheme.colorScheme.outlineVariant
    val titleColor = if (isExpanded) textColor else MaterialTheme.colorScheme.onSurface

    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onToggle),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, cardBorder, RoundedCornerShape(10.dp))
                .padding(14.dp)
        ) {
            Column {
                // ── Header row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Category icon pill
                    Text(
                        text = tip.category.emoji,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = tip.title,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = titleColor,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(2.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            CategoryBadge(tip.category)
                            if (tip.savingsEstimate.isNotBlank()) {
                                Text(
                                    text = "· ${tip.savingsEstimate}",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = if (isExpanded) border else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // ── Expandable detail section
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = expandVertically(),
                    exit  = shrinkVertically()
                ) {
                    Column {
                        Spacer(Modifier.height(10.dp))
                        HorizontalDivider(color = border.copy(alpha = 0.3f))
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = tip.description,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = textColor
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DifficultyBadge(difficulty = tip.difficulty, accentColor = border)
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────
//  Small reusable badge composables
// ─────────────────────────────────────────────

@Composable
private fun CategoryBadge(category: TipCategory) {
    val (bg, border, text) = categoryColors(category)
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(bg)
            .border(0.5.dp, border, CircleShape)
            .padding(horizontal = 6.dp, vertical = 1.dp)
    ) {
        Text(
            text = category.label,
            fontSize = 10.sp,
            color = text,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun DifficultyBadge(difficulty: String, accentColor: Color) {
    val icon = when (difficulty) {
        "Easy"   -> "🟢"
        "Medium" -> "🟡"
        "Hard"   -> "🔴"
        else     -> "⚪"
    }
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(0.5.dp, accentColor.copy(alpha = 0.4f), CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = "$icon  $difficulty",
            fontSize = 11.sp,
            color = accentColor
        )
    }
}

// ─────────────────────────────────────────────
//  Empty state
// ─────────────────────────────────────────────

@Composable
private fun EmptyState(query: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("🔍", fontSize = 36.sp)
        Text(
            text = if (query.isNotBlank()) "No tips found for \"$query\""
            else "No tips in this category",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Try a different search term or category",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ─────────────────────────────────────────────
//  Navigation Button
// ─────────────────────────────────────────────

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