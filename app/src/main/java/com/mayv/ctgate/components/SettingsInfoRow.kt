package com.mayv.ctgate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsInfoRow(
    modifier: Modifier = Modifier,
    headerText: String,
    descriptionText: String
    ) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            text = headerText,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Right
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            text = descriptionText,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Right
        )
    }
}