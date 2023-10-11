package com.mayv.ctgate.screens.exits

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.model.Log

@Composable
fun ExitsScreen(navController: NavController){

    val list = createDummyData()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)) {

                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(top = 10.dp, start = 7.dp, bottom = 10.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "MenuButton",
                        tint = colorResource(id = R.color.gradient_color)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(items = list, itemContent = { item ->

                    ListItem(log = item, list.indexOf(item) + 1)

                })
            }

        }
    }
}


@Composable
private fun ListItem(log: Log, number: Int) {

    Card(
        shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = R.color.red
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
                .background(colorResource(id = R.color.red))
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
                text = log.name,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = log.service.plus(" - ").plus(log.nationalId),
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}

private fun createDummyData(): List<Log> {
    return listOf(
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
        Log(
            "محمود احمد صلاح الدين عبدلحميد ابراهيم",
            "30003280201298",
            "دورية صباحية - امام الجوازات بالعباسية"
        ),
    ).asReversed()
}