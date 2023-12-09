package com.mayv.ctgate.screens.count

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.LogItem
import com.mayv.ctgate.components.LogItemShimmer
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.components.ServerError
import com.mayv.ctgate.navigation.AppScreens
import com.mayv.ctgate.utils.DateFormat
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.token
import com.mayv.ctgate.utils.funs.getGateSubtype

@Composable
fun CountScreen(
    navigationController: NavController,
    viewModel: CountViewModel = hiltViewModel()
) {

    val preferences = PreferenceHelper.getPreference(LocalContext.current)

    LaunchedEffect(key1 = Unit) {
        viewModel.allLogs(token = preferences.token!!)
    }


    MainSurface(navigationController, viewModel, preferences)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(
    navigationController: NavController,
    viewModel: CountViewModel,
    preferences: SharedPreferences
) {

    var isConnectionLost by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_grey)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                propagateMinConstraints = false
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.primary_color))
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        BackButton(
                            modifier = Modifier,
                            navController = navigationController
                        )

                        OutlinedTextInputField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 15.dp),
                            hint = stringResource(id = R.string.name),
                            hintColor = Color.White,
                            enabled = !isConnectionLost,
                            colors = TextFieldDefaults
                                .outlinedTextFieldColors(
                                    textColor = Color.White,
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    unfocusedLabelColor = Color.White,
                                    disabledLabelColor = Color.White,
                                    cursorColor = Color.White
                                ),
                            onDoneClicked = {
                                if (it.isNotEmpty() && it.isBlank()){
                                    //viewModel.soldierLogs(it, preferences.token!!)
                                }
                            }
                        )
                    }
                }
            }

            LogsList(navigationController, viewModel, lostConnection = { isConnectionLost = it })

        }
    }
}

@Composable
private fun LogsList(
    navController: NavController,
    viewModel: CountViewModel,
    lostConnection: (Boolean) -> Unit
) {

    val allLogs by viewModel.data.collectAsState()

    if (!viewModel.isLoading && viewModel.isSuccessful) {

        //lostConnection(false)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            allLogs.data?.let {
                items(items = it) { log ->

                    val status =
                        if (log.sub_type == 8 || log.sub_type == 9) log.time_section.plus(" - ")
                            .plus(log.section).plus(" - ")
                            .plus(log.service_location) else getGateSubtype(log.sub_type)

                    LogItem(
                        name = log.soldier.name,
                        nationalId = log.soldier.national_id,
                        service = status,
                        dateTime = DateFormat.reformatLogDate(log.date),
                        type = log.type
                    ) { nid ->
                        navController.navigate(AppScreens.SoldierScreen.name + "/${nid}")
                    }

                }
            }
        }
    } else if (!viewModel.isSuccessful) {

        lostConnection(true)

        Box(modifier = Modifier.fillMaxSize()) {
            ServerError(modifier = Modifier.align(Alignment.Center), "! انت غير مصرح لك")
        }

    } else if (viewModel.isLoading) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(items = listOf("", "", "", "", "", "", "", "", "")) {

                LogItemShimmer()
            }
        }
    }
}