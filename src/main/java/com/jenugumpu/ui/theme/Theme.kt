package com.jenugumpu.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JenuColors = lightColorScheme(
    primary = Color(0xFF6F4E1F),
    secondary = Color(0xFF2F6B4F),
    tertiary = Color(0xFFC47D20),
    background = Color(0xFFFAF8F2),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White
)

@Composable
fun JenuGumpuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = JenuColors,
        content = content
    )
}
