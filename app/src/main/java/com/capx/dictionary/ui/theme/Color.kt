package com.capx.dictionary.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Colors
val PrimaryAccent = Color(0xFF5B8CFF)
val SecondaryAccent = Color(0xFF7CFFB2)

// Background & Surfaces
val BackgroundColor = Color(0xFF0F1115)
val GlassSurface = Color(0xFFFFFFFF).copy(alpha = 0.08f)
val GlassBorder = Color(0xFFFFFFFF).copy(alpha = 0.12f)

// Text Colors
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF94A3B8)
val TextMuted = Color(0xFF64748B)

// Legacy compatibility (if needed for Material3 defaults)
val PrimaryColor = PrimaryAccent
val BackgroundColorDark = BackgroundColor
val BackgroundColorLight = Color(0xFFF8FAFC) // Keeping for light mode if kept

val TabBackgroundDark = GlassSurface
val TabBackgroundLight = Color(0xFFF1F5F9)
val TabSelectThumbDark = Color(0xFF1E293B)
val TabSelectThumbLight = Color(0xFFFFFFFF)

val CardColorDark = GlassSurface
val TextColorDark = TextPrimary
val TextColorLight = Color(0xFF020618)

val SubTextColorDark = TextSecondary
val SubTextColorLight = TextMuted

val NavigationUnselectedColor = Color(0xFF686F7D)