package com.mayv.ctgate.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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

@Composable
fun SearchListItem(
    name: String,
    nid: Long,
    unit: String,
    holidayGroup: String,
    policeNumber: Int,
    unitJob: String,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 5.dp)
            .clickable { onClick(nid) },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.soldiers_list_item_background)
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        border = BorderStroke(2.dp, colorResource(id = R.color.soldiers_list_item_border))
    ) {

        val group = "دفع"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp),
                text = nid.toString(),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                Text(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp),
                    text = policeNumber.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )

                Text(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp),
                    text = group.plus(" ").plus(holidayGroup),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )

                Text(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp),
                    text = unit,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp),
                text = unitJob,
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
        }
    }
}