package com.utbm.sy43.f_one_companion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary_color,
    background = Background_color,
    surface = Grey1_color,
    onPrimary = White_color,
    onBackground = White_color,
    onSurface = Grey3_color,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary_color,
    background = Background_color,
    surface = Grey1_color,
    onPrimary = White_color,
    onBackground = White_color,
    onSurface = Grey3_color,
)

@Composable
fun FOneCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}