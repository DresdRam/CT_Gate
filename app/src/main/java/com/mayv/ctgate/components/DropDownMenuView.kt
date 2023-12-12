package com.mayv.ctgate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.mayv.ctgate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuView(
    modifier: Modifier = Modifier,
    options: List<String> = listOf(""),
    onOptionChanged: (Int) -> Unit
) {

    var expandedState by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expandedState,
        onExpandedChange = { expandedState = !expandedState }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedType,
            onValueChange = {},
            textStyle = TextStyle.Default.copy(
                textDirection = TextDirection.Content,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.arabic))
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                disabledBorderColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray
            ),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) }
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .background(
                    color = Color.LightGray
                ),
            expanded = expandedState,
            onDismissRequest = { expandedState = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.arabic))
                        )
                    },
                    onClick = {
                        selectedType = option
                        expandedState = false
                        onOptionChanged(options.indexOf(option))
                    }
                )
            }
        }
    }
}