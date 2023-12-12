package com.mayv.ctgate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayv.ctgate.R
import com.mayv.ctgate.utils.PreferenceHelper.searchSize

@Composable
fun CounterButton(
    modifier: Modifier = Modifier,
    counterValue: String = "20",
    onAddClicked: () -> Unit,
    onRemoveClicked: () -> Unit
){
    Box(
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onRemoveClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "Minus",
                    tint = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.primary_color))
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .align(Alignment.Center),
                    text = counterValue,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

            IconButton(onClick = onAddClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Plus",
                    tint = Color.White
                )
            }
        }
    }
}