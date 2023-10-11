package com.mayv.ctgate.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun BannerText(
    modifier: Modifier,
    string: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    textColor: Color
) {
    Text(
        text = string,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        color = textColor,
        modifier = modifier,
    )
}