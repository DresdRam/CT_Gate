package com.mayv.ctgate.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackButton(modifier: Modifier, buttonColor: Color = Color.White, navController: NavController){
    Box(
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = "Back Button",
                tint = buttonColor
            )

        }
    }
}