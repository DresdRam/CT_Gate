package com.mayv.ctgate.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mayv.ctgate.R
import com.mayv.ctgate.utils.funs.shimmer

@Composable
fun SearchListItemShimmer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(15.dp)
            .background(
                color = colorResource(id = R.color.soldiers_list_item_background),
                shape = RoundedCornerShape(10.dp)
            )
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(30.dp)
                    .shimmer()
            )

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.LightGray)
                    .width(140.dp)
                    .height(30.dp)
                    .shimmer()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .width(60.dp)
                        .height(30.dp)
                        .shimmer()
                )

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .width(60.dp)
                        .height(30.dp)
                        .shimmer()
                )

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .width(60.dp)
                        .height(30.dp)
                        .shimmer()
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 25.dp, end = 25.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(30.dp)
                    .shimmer()
            )
        }
    }
}