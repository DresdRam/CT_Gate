package com.mayv.ctgate.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.CounterButton
import com.mayv.ctgate.components.CustomSwitch
import com.mayv.ctgate.components.SettingsInfoRow
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.excludeRemoved
import com.mayv.ctgate.utils.PreferenceHelper.ip
import com.mayv.ctgate.utils.PreferenceHelper.port
import com.mayv.ctgate.utils.PreferenceHelper.searchSize

@Composable
fun SettingsScreen(navigationController: NavController) {

    MainSurface(navigationController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(navigationController: NavController) {
    val preferences = PreferenceHelper.getPreference(LocalContext.current)

    var ip by remember { mutableStateOf(preferences.ip) }
    var port by remember { mutableStateOf(preferences.port) }
    var excludeRemoved by remember { mutableStateOf(preferences.excludeRemoved) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.primary_color))
            ) {

                BackButton(
                    modifier = Modifier,
                    navController = navigationController
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "الخادم",
                fontSize = 17.sp,
                color = colorResource(id = R.color.primary_color),
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                value = ip.toString(),
                label = "العنوان",
                onValueChange = {
                    ip = it
                    if (it.isNotEmpty()) {
                        preferences.ip = it
                    }
                })

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                value = port.toString(),
                label = "المنفذ",
                onValueChange = {
                    port = it
                    if (it.isNotEmpty()) {
                        preferences.port = it
                    }
                }
            )

            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Gray
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "البحث",
                fontSize = 17.sp,
                color = colorResource(id = R.color.primary_color),
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CustomSwitch(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    triggered = excludeRemoved,
                    onCheckedChange = {
                        excludeRemoved = it
                        preferences.excludeRemoved = it
                    }
                )

                SettingsInfoRow(
                    modifier = Modifier.weight(5f),
                    headerText = "استثناء المشطوبين",
                    descriptionText = "استثناء جميع المجندين المشطوبين حيث لا يظهر لك اي مجند مشطوب اثناء البحث"
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            var searchSize by remember { mutableIntStateOf(preferences.searchSize) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CounterButton(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.semi_primary)),
                    counterValue = searchSize.toString(),
                    onAddClicked = {
                        if (searchSize < 50) {
                            searchSize++
                            preferences.searchSize = searchSize
                        }
                    },
                    onRemoveClicked = {
                        if (searchSize > 5) {
                            searchSize--
                            preferences.searchSize = searchSize
                        }
                    }
                )

                SettingsInfoRow(
                    headerText = "حجم البحث",
                    descriptionText = "اقصي عدد للمجندين الذين سيظهرون في البحث"
                )

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = colorResource(id = R.color.hint_color),
                fontFamily = FontFamily(Font(R.font.arabic))
            )
        },
        modifier = modifier,
        singleLine = true,
        textStyle = TextStyle.Default.copy(
            textDirection = TextDirection.Content,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.arabic)),
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black,
            unfocusedBorderColor = colorResource(id = R.color.gradient_color),
            disabledBorderColor = Color.Gray,
            focusedLabelColor = colorResource(id = R.color.hint_color),
            unfocusedLabelColor = colorResource(id = R.color.hint_color)
        )
    )
}
