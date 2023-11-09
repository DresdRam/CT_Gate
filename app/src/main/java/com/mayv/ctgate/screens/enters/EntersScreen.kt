package com.mayv.ctgate.screens.enters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.model.GateLog

@Composable
fun EntersScreen(mainNavController: NavController, drawerNavController: NavController) {

    val list = createDummyData()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(items = list, itemContent = { item ->

                ListItem(gateLog = item, list.indexOf(element = item) + 1)

            })
        }

    }
}


@Composable
private fun ListItem(gateLog: GateLog, number: Int) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = R.color.green
            )
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.green))
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = number.toString(),
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(
                            Alignment.CenterEnd
                        )
                        .padding(top = 10.dp, end = 10.dp)
                )

            }

            Text(
                text = gateLog.soldier.name,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = gateLog.service_location.plus(" - ").plus(gateLog.soldier.national_id),
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}

private fun createDummyData(): List<GateLog> {
    return listOf(
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
        GateLog(),
    ).asReversed()
}