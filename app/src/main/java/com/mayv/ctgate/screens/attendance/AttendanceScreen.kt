package com.mayv.ctgate.screens.attendance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.DropDownMenuView
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.screens.search.SearchViewModel
import com.mayv.ctgate.utils.Constants
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.searchSize
import com.mayv.ctgate.utils.PreferenceHelper.token
import kotlinx.coroutines.launch

@Composable
fun AttendanceScreen(
    navigationController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    MainSurface(navigationController, searchViewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainSurface(navigationController: NavController, searchViewModel: SearchViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.HalfExpanded,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    var name by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }

    val preferences = PreferenceHelper.getPreference(LocalContext.current)
    var selectedTypeIndex by remember { mutableIntStateOf(0) }
    var selectedSectionIndex by remember { mutableIntStateOf(0) }
    var selectedTimeSectionIndex by remember { mutableIntStateOf(0) }
    var selectedServiceIndex by remember { mutableIntStateOf(0) }

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        sheetState = modalSheetState,
        sheetContentColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetContent = { BottomSheetContent(name = name, unit = unit) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                // If Dawrya Is Selected.
                if (selectedTypeIndex == 0) {
                    DropDownMenuView(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 20.dp, end = 10.dp),
                        options = Constants.getSectionOptions()
                    ) { optionIndex ->
                        selectedSectionIndex = optionIndex
                    }
                }

                DropDownMenuView(
                    modifier = if (selectedTypeIndex == 0) Modifier
                        .weight(1f)
                        .padding(start = 10.dp, end = 20.dp) else Modifier
                        .weight(1f)
                        .padding(start = 30.dp, end = 30.dp),
                    options = Constants.getTypeOptions()
                ) { optionIndex ->
                    selectedTypeIndex = optionIndex
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                // If Dawrya Is Selected.
                if (selectedTypeIndex == 0) {

                    DropDownMenuView(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 20.dp, end = 10.dp),
                        options = getSelectedSection(selectedSectionIndex)
                    ) { optionIndex ->
                        selectedServiceIndex = optionIndex
                    }

                    DropDownMenuView(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp, end = 20.dp),
                        options = Constants.getTimeSectionOptions()
                    ) { optionIndex ->
                        selectedTimeSectionIndex = optionIndex
                    }
                }
            }

            // If Tashrefa Is Selected.
            if (selectedTypeIndex == 1) {
                DropDownMenuView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    options = Constants.getTimeSectionOptions()
                ) { optionIndex ->
                    selectedTimeSectionIndex = optionIndex
                }

                OutlinedTextInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    hint = "اسم الخدمة",
                    onDoneClicked = { }
                )
            }

            OutlinedTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                hint = "اسم المجند",
                onDoneClicked = {
                    if (it.isNotEmpty()) {
                        searchViewModel.search(
                            name = it,
                            size = preferences.searchSize,
                            excludeRemoved = true,
                            authToken = preferences.token!!
                        )
                    }
                }
            )

            // TESTING!!!!!!!!!!
            // {

            val listOfSoldiers =
                if (!searchViewModel.isLoading && searchViewModel.isSuccessful) searchViewModel.data.collectAsState().value.data!! else listOf()

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = listOfSoldiers) { soldier ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    name = soldier.name
                                    unit = soldier.enrollment.unit.name
                                    modalSheetState.show()
                                }
                            },
                        text = soldier.name,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // }
            // TESTING!!!!!!!!!!
        }
    }
}


@Composable
fun BottomSheetContent(name: String, unit: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {

            Column(modifier = Modifier.weight(4f)) {

                Text(modifier = Modifier.fillMaxWidth(), text = name, color = Color.Black, textAlign = TextAlign.Right)
                Text(modifier = Modifier.fillMaxWidth(), text = unit, color = Color.Black, textAlign = TextAlign.Right)
                Text(modifier = Modifier.fillMaxWidth(), text = "دفع ثاني", color = Color.Black, textAlign = TextAlign.Right)

            }

            Box(
                modifier = Modifier.weight(2f)
            ) {
                Card(
                    modifier = Modifier.size(110.dp, 170.dp).align(Alignment.Center),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.cardElevation(15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_download),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = stringResource(id = R.string.soldier_image),
                        contentScale = ContentScale.Inside
                    )
                }
            }
        }
    }
}

private fun getSelectedSection(sectionIndex: Int): List<String> {
    return when (sectionIndex) {
        0 -> {
            Constants.getNewCairoOptions()
        }

        1 -> {
            Constants.getEastOptions()
        }

        2 -> {
            Constants.getWestOptions()
        }

        3 -> {
            Constants.getNorthOptions()
        }

        4 -> {
            Constants.getSouthOptions()
        }

        else -> {
            Constants.getNewCairoOptions()
        }
    }

}