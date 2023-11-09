package com.mayv.ctgate.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.mayv.ctgate.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun OutlinedTextInputField(
    modifier: Modifier,
    text: String = "",
    hint: String,
    align: TextAlign = TextAlign.Justify,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onDoneClicked: (String) -> Unit
) {
    var value by remember { mutableStateOf(text) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        enabled = enabled,
        label = {
            Text(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = colorResource(id = R.color.hint_color)
            )
        },
        modifier = modifier,
        singleLine = singleLine,
        textStyle = TextStyle(
            textDirection = TextDirection.Content,
            color = Color(0xFF000000),
            textAlign = align
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                onDoneClicked(value)
            }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = colorResource(id = R.color.gradient_color),
            disabledBorderColor = Color.Gray
        )
    )
}