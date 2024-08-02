package com.piyushprajpti.pyquote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShareImagePrototype(
    quoteData: QuoteEntity,
    backColor: Color,
    textColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = quoteData.content,
            fontSize = 22.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily(Font(R.font.playwrite)),
            lineHeight = 35.sp,
            color = textColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "~ " + quoteData.author,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.playwrite)),
            color = textColor
        )
    }
}