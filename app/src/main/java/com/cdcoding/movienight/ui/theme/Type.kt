package com.cdcoding.movienight.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cdcoding.movienight.R

val fonts = FontFamily(
    Font(R.font.archivo_regular),
    Font(R.font.archivo_bold, weight = FontWeight.Bold),
    Font(R.font.archivo_medium, weight = FontWeight.Medium),
    Font(R.font.archivo_semibold, weight = FontWeight.SemiBold),
    Font(R.font.archivo_italic, style = FontStyle.Italic),
    Font(R.font.archivo_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.archivo_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.archivo_semibold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)