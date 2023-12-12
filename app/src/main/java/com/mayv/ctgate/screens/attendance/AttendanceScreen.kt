package com.mayv.ctgate.screens.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.DropDownMenuView
import com.mayv.ctgate.components.OutlinedTextInputField
import com.mayv.ctgate.utils.Constants

@Composable
fun AttendanceScreen(navigationController: NavController) {
    MainSurface(navigationController)
}

@Composable
private fun MainSurface(navigationController: NavController) {

    var selectedTypeIndex by remember { mutableIntStateOf(0) }
    var selectedSectionIndex by remember { mutableIntStateOf(0) }
    var selectedTimeSectionIndex by remember { mutableIntStateOf(0) }
    var selectedServiceIndex by remember { mutableIntStateOf(0) }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
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

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)) {
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
                onDoneClicked = { }
            )
        }
    }
}

private fun getSelectedSection(sectionIndex: Int): List<String> {
    return when(sectionIndex){
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