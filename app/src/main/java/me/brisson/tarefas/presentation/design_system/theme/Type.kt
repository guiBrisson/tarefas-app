package me.brisson.tarefas.presentation.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.tarefas.R

val DMSans = FontFamily(
    Font(resId = R.font.dm_sans_regular),
    Font(resId = R.font.dm_sans_medium, weight = FontWeight.Medium),
    Font(resId = R.font.dm_sans_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = DMSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleLarge = TextStyle(
        fontFamily = DMSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyMedium = TextStyle(
        fontFamily = DMSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = TextStyle(
        fontFamily = DMSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )
)
