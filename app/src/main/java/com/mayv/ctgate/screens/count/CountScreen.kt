package com.mayv.ctgate.screens.count

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mayv.ctgate.components.ServerConnectionError
import com.mayv.ctgate.utils.getGateSubtype
import com.mayv.ctgate.utils.reformatDate

@Composable
fun CountScreen(
    navigationController: NavController,
    viewModel: CountViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.allLogs()
    }


    MainSurface(navigationController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(navigationController: NavController, viewModel: CountViewModel) {

    var isConnectionLost by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_grey)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
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
                                viewModel.soldierLogs(30003280201298)
                            }
                        )
                    }

                }
            }

            LogsList(viewModel) {
                isConnectionLost = !isConnectionLost
            }

        }
    }
}

@Composable
private fun LogsList(viewModel: CountViewModel, lostConnection: () -> Unit) {

    val allLogs by viewModel.data.collectAsState()

    if (!allLogs.failed) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (allLogs.loading) {
                items(items = listOf("", "", "", "", "", "")) {

                    LogItemShimmer()
                }
            } else {
                if (allLogs.data != null) {
                    items(items = allLogs.data!!) { log ->

                        val status =
                            if (log.sub_type == 8 || log.sub_type == 9) log.time_section.plus(" - ")
                                .plus(log.section).plus(" - ")
                                .plus(log.service_location) else getGateSubtype(log.sub_type)

                        LogItem(
                            name = log.soldier.name,
                            service = status,
                            dateTime = reformatDate(log.date),
                            type = log.type
                        )
                    }
                }
            }
        }
    } else {
        lostConnection()
        ServerConnectionError(modifier = Modifier.size(160.dp))
    }
}