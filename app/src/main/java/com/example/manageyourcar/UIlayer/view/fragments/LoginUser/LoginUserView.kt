package com.example.manageyourcar.UIlayer.view.fragments.LoginUser

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.LoginUiState
import com.example.manageyourcar.UIlayer.view.common.CustomDialog
import com.example.manageyourcar.UIlayer.view.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.UserLoginEvent

@Composable
fun LoginUserView(
    onEvent: (UserLoginEvent) -> Unit = {},
    uiState: LoginUiState
) {
    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )
    var displayPopup by remember { mutableStateOf(false) }

    if (displayPopup) {
        CustomDialog(
            onDismiss = {
                displayPopup = false
            },
            title = stringResource(R.string.password_forgot),
            content = stringResource(R.string.password_forgot_text)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.connection),
            fontSize = 32.sp,
            fontFamily = juraFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(20.dp))
        uiState.userLogin?.let {
            CustomTextField(
                textStyle = TextStyle(
                    background = Color.Transparent,
                    color = Color.White,
                    fontSize = 18.sp,
                ),
                imeAction = ImeAction.Next,
                error = uiState.userLoginError ?: "",
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .background(Color.Transparent),
                textFieldValue = it,
                placeholder = stringResource(id = R.string.id),
                readOnly = false,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    onEvent(UserLoginEvent.OnLoginChanged(it))
                }
            )
        }
        uiState.userPassword?.let {
            CustomTextField(
                textStyle = TextStyle(
                    background = Color.Transparent,
                    color = Color.White,
                    fontSize = 18.sp,
                ), visualTransformation = PasswordVisualTransformation(),
                error = uiState.userPasswordError ?: "",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(onNext = { onEvent(UserLoginEvent.OnClickSendButton) }),
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                placeholder = stringResource(id = R.string.password),
                textFieldValue = it,
                readOnly = false,
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    onEvent(UserLoginEvent.OnPasswordChanged(it))
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onEvent(UserLoginEvent.OnClickSendButton) },
            modifier = Modifier
                .width(208.dp),
            colors = ButtonDefaults.buttonColors(colorScheme.onSecondary)
        ) {
            Text(
                text = stringResource(id = R.string.connection),
                fontFamily = juraFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
            ) {
                append(stringResource(R.string.forgot_your_password))
            }
        }
        ClickableText(
            text = text,
            style = TextStyle(
                fontFamily = juraFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            modifier = Modifier.padding(8.dp),
            onClick = {
                displayPopup = true
            }
        )
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedButton(
            border = BorderStroke(5.dp, Color(209, 228, 255)),
            shape = CircleShape,
            onClick = {
                onEvent(UserLoginEvent.OnSignInButton)
            },
            modifier = Modifier
                .width(245.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                color = Color.White,
                fontFamily = juraFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 3.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginView() {
    LoginUserView(uiState = LoginUiState())
}