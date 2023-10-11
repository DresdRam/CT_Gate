package com.mayv.ctgate.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    var startAnimation by remember { mutableStateOf(false) }
    val alpha = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 2000),
        label = "Logo Alpha Animation"
    )

    Splash(alpha = alpha.value)

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)
        navController.popBackStack()
        navController.navigate(AppScreens.DrawerScreen.name)
    }

}

@Composable
private fun Splash(alpha: Float) {
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.primary_color)) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(alpha = alpha)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.cairo_traffic_gate),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .alpha(alpha),
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.cairo_traffic_logo),
                    contentDescription = "Splash Logo",
                    modifier = Modifier
                        .alpha(alpha)
                        .size(350.dp)
                        .padding(top = 40.dp)
                )

                Text(
                    text = stringResource(id = R.string.ct_footer),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .alpha(alpha),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Text(
                    text = stringResource(id = R.string.ct_management),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .alpha(alpha),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )

                Text(
                    text = stringResource(id = R.string.ct_manager),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha),
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.diwani)),
                )
            }
        }
    }
} 