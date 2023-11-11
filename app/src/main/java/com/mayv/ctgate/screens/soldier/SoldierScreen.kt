package com.mayv.ctgate.screens.soldier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.components.RoundedButton
import com.mayv.ctgate.components.ServerConnectionError
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.utils.DateType
import com.mayv.ctgate.utils.reformatDate
import com.mayv.ctgate.utils.shimmer

@Composable
fun SoldierScreen(navController: NavController, viewModel: SoldierViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        viewModel.soldierData(30003280201298)
        viewModel.soldierImage(30003280201298)
    }

    MainScaffold(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(
    navController: NavController,
    viewModel: SoldierViewModel
) {

    val soldierData by viewModel.data.collectAsState()
    val soldierImage by viewModel.image.collectAsState()

    //val conf = Bitmap.Config.ARGB_8888
    //val bitmap = Bitmap.createBitmap(20, 20, conf)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            if (!soldierData.loading && !soldierData.failed) {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent
                ) {
                    BottomBarContent()
                }
            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = colorResource(id = R.color.background_grey)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                propagateMinConstraints = false
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(0.dp, 0.dp, 100.dp, 100.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.primary_color))
                ) {
                    BackButton(
                        modifier = Modifier.fillMaxWidth(),
                        navController = navController
                    )
                }
            }

            if (!soldierData.failed && !soldierData.loading) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(paddingValues = paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) {

                    Box(
                        modifier = Modifier
                            .padding(top = 40.dp)
                    ) {
                        Card(
                            modifier = Modifier.size(240.dp, 340.dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(15.dp)
                        ) {
                            (soldierImage.data)?.let {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = stringResource(id = R.string.soldier_image),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }

                    StatusCard(soldierData)

                    InfoCard(soldierData)

                }
            } else if (soldierData.failed) {

                ServerConnectionError(modifier = Modifier.size(120.dp))
            }
        }
    }
}

@Composable
private fun InfoCard(
    data: Resource<Soldier>
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp, bottom = 25.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        if (data.data?.name != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.name),
                align = TextAlign.Center,
                enabled = false,
                text = data.data!!.name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }
        }

        if (data.data?.national_id != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.national_id),
                align = TextAlign.Center,
                enabled = false,
                text = data.data!!.national_id.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            if (data.data?.enrollment?.holiday_group != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.holiday_group),
                    align = TextAlign.Center,
                    enabled = false,
                    text = data.data!!.enrollment.holiday_group,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 15.dp, end = 5.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }

            if (data.data?.enrollment?.police_number != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.police_numer),
                    align = TextAlign.Center,
                    enabled = false,
                    text = data.data!!.enrollment.police_number.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 5.dp, end = 15.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            if (data.data?.enrollment?.unit?.name != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.unit),
                    align = TextAlign.Center,
                    enabled = false,
                    text = data.data!!.enrollment.unit.name,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 15.dp, end = 5.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }

            if (data.data?.enrollment?.enrollment_date != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.enrollment_date),
                    align = TextAlign.Center,
                    enabled = false,
                    text = reformatDate(data.data!!.enrollment.enrollment_date, DateType.InfoDate),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 5.dp, end = 15.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            if (data.data?.phone_number != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.phone_number),
                    align = TextAlign.Center,
                    enabled = false,
                    text = data.data!!.phone_number,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 15.dp, end = 5.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }

            if (data.data?.qualification != null) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.education),
                    align = TextAlign.Center,
                    enabled = false,
                    text = data.data!!.qualification,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 5.dp, end = 15.dp)
                        .weight(1f)
                )
                {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .shimmer()
                    )
                }
            }
        }

        if (data.data?.medical_condition != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.medical_type),
                align = TextAlign.Center,
                enabled = false,
                text = data.data!!.medical_condition,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }
        }

        if (data.data?.address?.get(0)?.governorate?.name != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.governorate),
                align = TextAlign.Center,
                enabled = false,
                text = data.data!!.address[0].governorate.name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }
        }

        if (data.data?.enrollment?.unit_job != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.unit_job),
                align = TextAlign.Center,
                enabled = false,
                text = data.data!!.enrollment.unit_job,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }
        }

        if (data.data?.getNotesAsString() != null) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    ),
                hint = stringResource(id = R.string.notes),
                align = TextAlign.Center,
                singleLine = false,
                enabled = false,
                text = data.data!!.getNotesAsString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
            )
            {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(200.dp)
                        .shimmer()
                )
            }
        }
    }
}

@Composable
private fun BottomBarContent() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RoundedButton(
            modifier = Modifier
                .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                .weight(1f),
            text = "خروج",
            onClick = {}
        )

        RoundedButton(
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                .weight(1f),
            text = "دخول",
            onClick = {}
        )
    }
}

@Composable
private fun StatusCard(
    data: Resource<Soldier>
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StatusItemWithIcon("ترقب الوصول", R.drawable.ic_bell, Color.Green)

            StatusItemWithIcon("الحالة الصحية", R.drawable.ic_check, Color.Green)

            StatusItemWithText("التقييم", "أ")

            StatusItemWithText("نوع التقييم", "-")
        }

    }
}

@Composable
private fun StatusItemWithText(statusText: String, status: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd), text = statusText,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterStart),
            text = status
        )
    }
}

@Composable
private fun StatusItemWithIcon(text: String, ic: Int, tint: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd), text = text,
            textAlign = TextAlign.Center
        )

        Icon(
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(id = ic),
            contentDescription = "Status Icon",
            tint = tint
        )
    }
}