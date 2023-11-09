package com.mayv.ctgate.screens.count

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.LogItem
import com.mayv.ctgate.components.LogItemShimmer
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.components.serverConnectionError
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.utils.getGateSubtype
import com.mayv.ctgate.utils.reformatDate
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CountScreen(
    mainNavController: NavController,
    drawerNavController: NavController,
    viewModel: CountViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.allLogs()
    }

    var allLogs = viewModel.data.collectAsState()

    MainSurface(allLogs.value, viewModel)
}

@Composable
fun MainSurface(allLogs: Resource<List<GateLog>>, viewModel: CountViewModel) {
    var list = (allLogs.data) ?: listOf(GateLog(), GateLog(), GateLog(), GateLog(), GateLog())
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_grey)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            propagateMinConstraints = false
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {

                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 15.dp),
                    hint = stringResource(id = R.string.name),
                    onDoneClicked = {
                        viewModel.soldierLogs(30003280201298)
                        list = (viewModel.data.value.data) ?: listOf(GateLog(), GateLog(), GateLog(), GateLog(), GateLog())
                    }
                )

            }
        }

        if (!allLogs.failed) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 85.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (allLogs.loading) {
                    items(items = list) {

                        LogItemShimmer()
                    }
                } else {
                    items(items = list) { log ->

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
        } else {
            serverConnectionError(modifier = Modifier.size(160.dp))
        }
    }
}