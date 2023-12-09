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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
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
import com.mayv.ctgate.components.ServerError
import com.mayv.ctgate.utils.DateFormat
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.token
import com.mayv.ctgate.utils.funs.shimmer

@Composable
fun SoldierScreen(
    navController: NavController,
    nationalId: Long,
    viewModel: SoldierViewModel = hiltViewModel()
) {

    val preferences = PreferenceHelper.getPreference(LocalContext.current)

    LaunchedEffect(key1 = true) {
        viewModel.soldierData(nationalId, preferences.token!!)
        viewModel.soldierImage(nationalId, preferences.token!!)
    }

    MainScaffold(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(
    navController: NavController,
    viewModel: SoldierViewModel
) {

    val soldierImage by viewModel.image.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
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
                        if (!viewModel.isImgLoading && viewModel.isImgSuccessful) {
                            (soldierImage.data)?.let {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = stringResource(id = R.string.soldier_image),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        } else if (viewModel.isImgLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmer(),
                                content = {}
                            )
                        }
                    }
                }

                StatusCard(viewModel)

                InfoCard(viewModel)

                if (!viewModel.isDataSuccessful) {

                    ServerError(modifier = Modifier.size(120.dp))
                }

            }
        }
    }
}

@Composable
private fun InfoCard(
    viewModel: SoldierViewModel
) {

    val data by viewModel.data.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp, bottom = 25.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        data.data?.let {

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    hint = stringResource(id = R.string.name),
                    align = TextAlign.Center,
                    enabled = false,
                    text = it.name,
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

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    hint = stringResource(id = R.string.national_id),
                    align = TextAlign.Center,
                    enabled = false,
                    text = it.national_id.toString(),
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
                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 15.dp, end = 5.dp),
                        hint = stringResource(id = R.string.holiday_group),
                        align = TextAlign.Center,
                        enabled = false,
                        text = it.enrollment.holiday_group,
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

                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 5.dp, end = 15.dp),
                        hint = stringResource(id = R.string.police_numer),
                        align = TextAlign.Center,
                        enabled = false,
                        text = it.enrollment.police_number.toString(),
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
                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 15.dp, end = 5.dp),
                        hint = stringResource(id = R.string.unit),
                        align = TextAlign.Center,
                        enabled = false,
                        text = it.enrollment.unit.name,
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

                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 5.dp, end = 15.dp),
                        hint = stringResource(id = R.string.enrollment_date),
                        align = TextAlign.Center,
                        enabled = false,
                        text = DateFormat.reformatDate(it.enrollment.enrollment_date),
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
                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 15.dp, end = 5.dp),
                        hint = stringResource(id = R.string.phone_number),
                        align = TextAlign.Center,
                        enabled = false,
                        text = it.phone_number,
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

                if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 5.dp, end = 15.dp),
                        hint = stringResource(id = R.string.education),
                        align = TextAlign.Center,
                        enabled = false,
                        text = it.qualification,
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

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                it.medical_condition.let { md ->
                    var medicalCondition = "جيد"
                    if (!md.isNullOrEmpty()) {
                        medicalCondition = md
                    }
                    OutlinedTextInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                        hint = stringResource(id = R.string.medical_type),
                        align = TextAlign.Center,
                        enabled = false,
                        text = medicalCondition,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onDoneClicked = {}
                    )
                }
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

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                var governorate = ""
                if (it.address.isNotEmpty()) {
                    governorate = it.address[0].governorate.name
                }
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    hint = stringResource(id = R.string.governorate),
                    align = TextAlign.Center,
                    enabled = false,
                    text = governorate,
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

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    hint = stringResource(id = R.string.unit_job),
                    align = TextAlign.Center,
                    enabled = false,
                    text = it.enrollment.unit_job,
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

            if (!viewModel.isDataLoading && viewModel.isDataSuccessful) {
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
                    text = it.getNotesAsString(),
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
    viewModel: SoldierViewModel
) {
    val data by viewModel.data.collectAsState()

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

            if (!viewModel.isDataLoading) {
                data.data?.let {

                    StatusItemWithIcon(
                        "ترقب الوصول",
                        R.drawable.ic_bell,
                        if (it.rating_status) Color.Red else Color.Green
                    )
                    StatusItemWithIcon(
                        "الحالة الصحية",
                        R.drawable.ic_check,
                        if (it.medical_condition == "جيد") Color.Green else Color.Red
                    )
                    StatusItemWithText("التقييم", it.rating)
                    StatusItemWithText("نوع التقييم", it.rating_type)
                }
            } else {
                ShimmerStatusItem()
                ShimmerStatusItem()
                ShimmerStatusItem()
                ShimmerStatusItem()
            }
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

@Composable
private fun ShimmerStatusItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd)
                .height(20.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmer()
        )

        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterStart)
                .height(20.dp)
                .width(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmer()
        )
    }
}