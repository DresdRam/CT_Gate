package com.mayv.ctgate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mayv.ctgate.utils.funs.shimmer


@Composable
fun LogItemShimmer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp, bottom = 10.dp, end = 108.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(start = 15.dp, end = 17.dp)
                        .align(Alignment.TopCenter)
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .shimmer()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(start = 25.dp, end = 27.dp)
                        .align(Alignment.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .shimmer()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(start = 35.dp, end = 37.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .shimmer()
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color.LightGray)
                .size(110.dp)
                .shimmer()
        )
    }
}