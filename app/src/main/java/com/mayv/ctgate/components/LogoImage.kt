package com.mayv.ctgate.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mayv.ctgate.R

@Composable
fun LogoImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.cairo_traffic_logo),
        contentDescription = stringResource(id = R.string.cairo_traffic_logo),
        modifier = modifier
    )
}