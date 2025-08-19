package com.example.myapplication.ui.RMTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val topAndBottomPadding_16 = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp)
val topAndBottomPadding_4 = Modifier.padding(0.dp, 4.dp, 0.dp, 4.dp)

val bottomPadding_8 = Modifier.padding(bottom = 8.dp)

val startAndTopPadding_8 = Modifier.padding(8.dp, 8.dp ,0.dp, 0.dp)

val allroundPadding_24 = Modifier.padding(24.dp)
val allroundPadding_16 = Modifier.padding(16.dp)

val titleStyle = TextStyle (
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.8.sp,
    color = Color.White,
    textAlign = TextAlign.Center
)

val subTitleStyle = TextStyle (
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.8.sp,
    color = Color.White,
    textAlign = TextAlign.Center
)

val logoTextStyle = TextStyle (
    color = Color.LightGray,
    fontSize = 14.sp,
    fontWeight = FontWeight.W700,
    letterSpacing = 0.8.sp,
)

val bodyTextStyleLarge = TextStyle (
    fontSize = 18.sp,
    letterSpacing = 0.6.sp,
    color = Color.LightGray
)

val bodyTextStyleMedium = TextStyle (
    fontSize = 16.sp,
    letterSpacing = 0.5.sp,
    color = Color.LightGray
)

val bodyTextStyleSmall = TextStyle (
    fontSize = 10.sp,
    letterSpacing = 0.4.sp,
    color = Color.White
)


val NeonBlueHex = Color(0xFF0BBFF5)
val NeonGreenHex = Color(0xFF47DA12)
val GreenGlassHex = Color(0x268EDA73)
val OrangeHex = Color(0xFFEF8300)
val HotPinkHex = Color(0xFFE000D4)

// Gradient
val horizontalGradientWarm = Brush.horizontalGradient(
    colors = listOf(Color.Yellow, Color.Magenta)
)

val horizontalGradientNeon = Brush.horizontalGradient(
    colors = listOf(NeonGreenHex, NeonBlueHex)
)

val horizontalGradientInfomation = Brush.horizontalGradient(
    colors = listOf(Color.Black.copy(alpha = 1f), HotPinkHex.copy(alpha = 0.6f))
)

val verticalGradientLogo = Brush.verticalGradient(
    colors = listOf(OrangeHex, Color.Yellow)
)

val verticalGradientTitleBackground = Brush.verticalGradient(
    colors = listOf(Color.Black, NeonGreenHex.copy(0.6f), Color.Black)
)

val horizontalGradientButton = Brush.horizontalGradient(
    colors = listOf(NeonGreenHex.copy(alpha = 0.3f), NeonGreenHex.copy(alpha = 0.6f))
)

val TitleBackgroundModifier = Modifier
    .fillMaxWidth()
    .background(brush = verticalGradientTitleBackground)

val BackgroundWallColor = Modifier
    .fillMaxSize()
    .background(Color.Black)
