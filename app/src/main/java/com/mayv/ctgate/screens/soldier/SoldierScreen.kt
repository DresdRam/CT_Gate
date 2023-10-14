package com.mayv.ctgate.screens.soldier

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.components.RoundedButton

@Composable
fun SoldierScreen(navController: NavController, viewModel: SoldierViewModel = viewModel()) {
    viewModel.soldierInfo(30003280201298)
    viewModel.soldierImage(30003280201298)

    MainScaffold(viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(viewModel: SoldierViewModel) {

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

            Box(modifier = Modifier.padding(top = 40.dp)) {
                Card(
                    modifier = Modifier
                        .size(260.dp, 340.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(15.dp)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.cairo_traffic_logo),
                        contentDescription = stringResource(id = R.string.soldier_image)
                    )
                }
            }

            StatusCard(viewModel)

            InfoCard(viewModel)

        }
    }
}

@Composable
private fun InfoCard(viewModel: SoldierViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp, bottom = 25.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            hint = stringResource(id = R.string.name),
            text = if (viewModel.soldierData.value.loading == false) viewModel.soldierData.value.data!!.name else "",
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            hint = stringResource(id = R.string.national_id),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 15.dp, end = 5.dp),
                hint = stringResource(id = R.string.holiday_group),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )

            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 5.dp, end = 15.dp),
                hint = stringResource(id = R.string.police_numer),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 15.dp, end = 5.dp),
                hint = stringResource(id = R.string.unit),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )

            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 5.dp, end = 15.dp),
                hint = stringResource(id = R.string.enrollment_date),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 15.dp, end = 5.dp),
                hint = stringResource(id = R.string.phone_number),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )

            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 5.dp, end = 15.dp),
                hint = stringResource(id = R.string.education),
                enabled = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onDoneClicked = {}
            )
        }

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            hint = stringResource(id = R.string.medical_type),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            hint = stringResource(id = R.string.governorate),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            hint = stringResource(id = R.string.unit_job),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

        OutlinedTextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
            hint = stringResource(id = R.string.notes),
            singleLine = false,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onDoneClicked = {}
        )

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
private fun StatusCard(viewModel: SoldierViewModel) {
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