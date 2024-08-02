package com.piyushprajpti.pyquote

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun randomColorGenerator(modifier: Modifier = Modifier): Pair<Color, Color> {

    val r = Random.nextInt(100, 200)
    val g = Random.nextInt(100, 200)
    val b = Random.nextInt(100, 200)

    val backgroundColorHex = Color(r,g,b)

    val brightness = (r * 299 + g * 587 + b * 114) / 1000

    val textColor = if (brightness > 128) Color(0xFF0A0A0A) else Color(0xFFE7E7E7)

    return Pair(backgroundColorHex, textColor)

}