package com.mayv.ctgate.screens.soldier

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.ui.theme.AppTheme

@Composable
fun SoldierScreen(navController: NavController) {
    MainSurface()
}

@Composable
fun MainSurface() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.hint_color)
    ) {

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

            StatusCard()

            InfoCard()

        }
    }
}

@Composable
fun InfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(start = 15.dp, end = 15.dp),
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        OutlinedTextInputField(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
            hint = stringResource(id = R.string.name),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onOkClicked = {}
        )

    }
}

@Composable
fun StatusCard() {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
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
fun StatusItemWithText(statusText: String, status: String) {
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
fun StatusItemWithIcon(text: String, ic: Int, tint: Color) {
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
@Preview
fun Prev() {
    AppTheme {
        MainSurface()
    }
}