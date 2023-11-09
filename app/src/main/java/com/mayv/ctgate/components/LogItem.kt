package com.mayv.ctgate.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayv.ctgate.R
import com.mayv.ctgate.utils.LogType.ENTER


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogItem(name: String = "", service: String = "", dateTime: String = "", type: String = ENTER) {
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
                modifier = Modifier.fillMaxSize().padding(start = 5.dp, end = 7.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 5.dp)
                        .basicMarquee(),
                    text = name,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .basicMarquee(),
                    text = service,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 3.dp),
                    text = dateTime,
                    color = if (type == ENTER) colorResource(id = R.color.green) else colorResource(
                        id = R.color.red
                    ),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
        }

        Card(
            modifier = Modifier
                .size(110.dp).align(Alignment.CenterEnd),
            colors = if (type == ENTER) CardDefaults.cardColors(colorResource(id = R.color.green)) else CardDefaults.cardColors(
                colorResource(id = R.color.red)
            ),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = type,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
        }
    }
}