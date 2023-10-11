package com.mayv.ctgate.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BannerText
import com.mayv.ctgate.components.LogoImage
import com.mayv.ctgate.components.OutlinedTextInputField


@Composable
fun HomeScreen(navController: NavController) {
    MainSurface()
}

@Composable
fun MainSurface() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                ),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                BannerText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    string = stringResource(id = R.string.main_gate),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textColor = colorResource(id = R.color.hint_color)
                )

                LogoImage(
                    Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .size(180.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                OutlinedTextInputField(
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 30.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    hint = stringResource(id = R.string.national_id),
                    enabled = true,
                    KeyboardOptions(keyboardType = KeyboardType.Number)
                ) { text ->
                    Log.i("OTIF", "OutlinedInputFieldText: $text")
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {
                        Divider(
                            modifier = Modifier
                                .weight(3f)
                                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                            color = colorResource(id = R.color.hint_color),
                            thickness = 1.dp
                        )

                        Text(
                            modifier = Modifier.weight(0.5f),
                            text = stringResource(id = R.string.or),
                            textAlign = TextAlign.Center,
                        )

                        Divider(
                            modifier = Modifier
                                .weight(3f)
                                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                            color = colorResource(id = R.color.hint_color),
                            thickness = 1.dp
                        )
                    }

                }

                OutlinedTextInputField(
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 10.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    hint = stringResource(id = R.string.name),
                    enabled = true,
                    KeyboardOptions(keyboardType = KeyboardType.Text)
                ) { text ->
                    Log.i("OTIF", "OutlinedInputFieldText: $text")
                }

                BannerText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
                    string = stringResource(id = R.string.ct_footer),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = colorResource(id = R.color.black)
                )
            }
        }
    }
}