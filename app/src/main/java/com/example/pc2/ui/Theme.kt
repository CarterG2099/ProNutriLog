// File: ui/theme/Theme.kt

package com.example.pc2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Royal = Color(0xFF4169E1)

private val LightColorPalette = lightColors(
    primary = Royal,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val Typography = androidx.compose.material.Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    // Add more text styles here
)

@Composable
fun MyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes, // You can define shapes if needed
        content = content
    )
}



// Shapes
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
