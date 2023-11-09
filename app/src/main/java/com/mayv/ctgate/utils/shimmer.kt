package com.mayv.ctgate.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmer(): Modifier = composed {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "Transition")
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "Start Offset"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFACAAAA),
                Color.White,
                Color(0xFFACAAAA)
            ),
            start = Offset(startOffset, 0f),
            end = Offset(startOffset + size.width.toFloat(), size.width.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}