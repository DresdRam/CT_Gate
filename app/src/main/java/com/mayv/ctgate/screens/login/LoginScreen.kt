package com.mayv.ctgate.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BannerText
import com.mayv.ctgate.components.CairoTrafficLogo
import com.mayv.ctgate.navigation.AppScreens
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.token

@Composable
fun LoginScreen(navigationController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    MainSurface(navigationController, viewModel)
}

@Composable
private fun MainSurface(navigationController: NavController, viewModel: LoginViewModel) {


    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            HeaderContent()

            var username by remember { mutableStateOf("gate") }

            var usernameEmpty by remember { mutableStateOf(false) }

            UsernameInputField(username, usernameEmpty) { newValue ->
                username = newValue
                usernameEmpty = username.isEmpty()
            }

            var password by remember { mutableStateOf("gate\$password#") }

            var passwordEmpty by remember { mutableStateOf(false) }

            PasswordInputField(password, passwordEmpty) { newValue ->
                password = newValue
                passwordEmpty = password.isEmpty()
            }

            val data by viewModel.data.collectAsState()

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (data.statusCode in (400..500)) 1f else 0f),
                text = if (data.statusCode in (400..500)) data.exception.message.toString() else "",
                textAlign = TextAlign.Center,
                color = Color.Red,
                fontSize = 12.sp,
            )

            LoginButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(65.dp),
                onClick = {
                    if (username.isEmpty()) {
                        usernameEmpty = true
                    }
                    if (password.isEmpty()) {
                        passwordEmpty = true
                    }

                    if (!usernameEmpty && !passwordEmpty) viewModel.login(username, password)

                }) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                    )
                } else {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = stringResource(id = R.string.enter),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.arabic)),
                        color = Color.White
                    )
                }
            }

            if (viewModel.isSuccessful) {
                val preferences = PreferenceHelper.getPreference(LocalContext.current)
                preferences.token = viewModel.token
                navigationController.popBackStack()
                navigationController.navigate(AppScreens.HomeScreen.name)
            }

        }
    }
}

@Composable
fun HeaderContent() {

    BannerText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        string = stringResource(id = R.string.dep_of_soldiers),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textColor = colorResource(id = R.color.hint_color)
    )

    CairoTrafficLogo(
        modifier = Modifier
            .size(180.dp)
    )

    BannerText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        string = stringResource(id = R.string.login),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textColor = colorResource(id = R.color.hint_color)
    )
}

@Composable
private fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary_color)
        ),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UsernameInputField(
    username: String,
    usernameEmpty: Boolean,
    onValueChanged: (String) -> Unit
) {

    OutlinedTextField(
        value = username,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = stringResource(id = R.string.username),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = colorResource(id = R.color.hint_color)
            )
        },
        isError = usernameEmpty,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp),
        singleLine = true,
        textStyle = TextStyle(
            textDirection = TextDirection.Content,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            errorBorderColor = Color.Red
        ),
        visualTransformation = VisualTransformation.None
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordInputField(
    password: String,
    passwordEmpty: Boolean,
    onValueChanged: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }


    OutlinedTextField(
        value = password,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = colorResource(id = R.color.hint_color)
            )
        },
        isError = passwordEmpty,
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisible = !passwordVisible
                },
            ) {
                Icon(
                    painter = if (passwordVisible) painterResource(id = R.drawable.ic_invisibile) else painterResource(
                        id = R.drawable.ic_visibile
                    ),
                    contentDescription = "Password Visibility Icon",
                    tint = Color(0xFF676a6c)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp),
        singleLine = true,
        textStyle = TextStyle(
            textDirection = TextDirection.Content,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            errorBorderColor = Color.Red
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}