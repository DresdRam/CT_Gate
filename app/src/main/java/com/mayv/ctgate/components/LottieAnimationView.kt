package com.mayv.ctgate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mayv.ctgate.R

@Composable
fun LottieAnimationView(
    modifier: Modifier,
    message: String = stringResource(id = R.string.no_conncetion),
    resource: Int
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            modifier = Modifier.size(240.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            color = colorResource(id = R.color.red),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}