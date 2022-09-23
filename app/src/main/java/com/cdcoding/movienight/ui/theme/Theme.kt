package com.cdcoding.movienight.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkNightBlue,
    onPrimary = Color.White,
    secondary = LightBlue,
    onSecondary = DarkNightBlue,
    background = NightBlue,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = DarkNightBlue,

)

private val LightColorPalette = lightColors(
    primary = DarkNightBlue,
    onPrimary = Color.White,
    secondary = LightBlue,
    onSecondary = DarkNightBlue,
    background = NightBlue,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = DarkNightBlue,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MovieNightTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}