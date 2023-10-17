package com.mayv.ctgate.screens.soldier

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.components.RoundedButton
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Note
import com.mayv.ctgate.model.Soldier
import okhttp3.internal.notify

@Composable
fun SoldierScreen(navController: NavController, viewModel: SoldierViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        viewModel.soldierData(30003280201298)
        viewModel.soldierImage(300030280201298)
    }

    val soldierData by viewModel.data.collectAsState()
    val soldierImage by viewModel.image.collectAsState()

    MainScaffold(soldierData, soldierImage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(
    data: Resource<Soldier>,
    image: Resource<Bitmap>
) {

    //val conf = Bitmap.Config.ARGB_8888
    //val bitmap = Bitmap.createBitmap(20, 20, conf)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colorResource(id = R.color.background_grey),
        bottomBar = {
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
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            propagateMinConstraints = false
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(0.dp, 0.dp, 100.dp, 100.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.primary_color)),
                content = {}
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {

            if (data.loading) {
                Log.d("TAG", "MainScaffold: loading -> ${data.loading}")
                Log.d("TAG", "MainScaffold: data -> ${data.data}")
                Log.d("TAG", "MainScaffold: exception -> ${data.exception?.message}")
            } else {
                Log.d("TAG", "MainScaffold: loading -> ${data.loading}")
                Log.d("TAG", "MainScaffold: data -> ${data.data}")
                Log.d("TAG", "MainScaffold: exception -> ${data.exception?.message}")
            }

            Box(modifier = Modifier.padding(top = 40.dp)) {
                Card(
                    modifier = Modifier
                        .size(240.dp, 340.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(15.dp)
                ) {
                    (image.data)?.let {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            bitmap = it.asImageBitmap(),
                            contentDescription = stringResource(id = R.string.soldier_image),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }

            StatusCard(data)

            InfoCard(data)

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

        (data.data?.name)?.let {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.name),
                enabled = false,
                text = it,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        (data.data?.national_id)?.let {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.national_id),
                enabled = false,
                text = it.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            (data.data?.enrollment?.holiday_group)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.holiday_group),
                    enabled = false,
                    text = it,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }

            (data.data?.enrollment?.police_number)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.police_numer),
                    enabled = false,
                    text = it.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            (data.data?.enrollment?.unit?.name)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.unit),
                    enabled = false,
                    text = it.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }

            (data.data?.enrollment?.enrollment_date)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.enrollment_date),
                    enabled = false,
                    text = it.split('T')[0],
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            (data.data?.phone_number)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 15.dp, end = 5.dp),
                    hint = stringResource(id = R.string.phone_number),
                    enabled = false,
                    text = it,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }

            (data.data?.qualification)?.let {
                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 5.dp, end = 15.dp),
                    hint = stringResource(id = R.string.education),
                    enabled = false,
                    text = it,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onDoneClicked = {}
                )
            }
        }

        (data.data?.medical_condition)?.let {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.medical_type),
                enabled = false,
                text = it,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        (data.data?.address?.get(0)?.governorate?.name)?.let {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.governorate),
                enabled = false,
                text = it,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        (data.data?.enrollment?.unit_job)?.let {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                hint = stringResource(id = R.string.unit_job),
                enabled = false,
                text = it,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        (data.data?.getNotesAsString())?.let {
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
                singleLine = false,
                enabled = false,
                text = it,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
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