package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.composeView.UIState.LoginUiState
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.UserLoginEvent

@Composable
fun LoginUserView(
    onEvent: (UserLoginEvent) -> Unit = {},
    uiState: LoginUiState
) {
    if (uiState.onInternetLost) {
        CustomDialog(title = "Internet perdu")
    } else {
        var displayPopup by remember { mutableStateOf(false) }

        if (displayPopup) {
            CustomDialog(
                onDismiss = {
                    displayPopup = false
                },
                title = "Mot de passe oublié?",
                content = "La flemme d'y géré maintenant tchouss"
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Connexion",
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            uiState.userLogin?.let {
                CustomTextField(
                    error = uiState.userLoginError ?: "",
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(10.dp),
                    textFieldValue = it,
                    label = "Identifiant",
                    readOnly = false,
                    keyboardType = KeyboardType.Text,
                    onValueChange = {
                        onEvent(UserLoginEvent.OnLoginChanged(it))
                    }
                )
            }
            uiState.userPassword?.let {
                CustomTextField(
                    visualTransformation = PasswordVisualTransformation(),
                    error = uiState.userPasswordError ?: "",
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(10.dp),
                    label = "Mot de passe",
                    textFieldValue = it,
                    readOnly = false,
                    keyboardType = KeyboardType.Password,
                    onValueChange = {
                        onEvent(UserLoginEvent.OnPasswordChanged(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { onEvent(UserLoginEvent.OnClickSendButton) }) {
                Text(
                    text = "Connexion",
                    fontSize = 20.sp
                )
            }
            val text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue
                    )
                ) {
                    append("Forgot your password?")
                }
            }
            ClickableText(
                text = text,
                modifier = Modifier.padding(8.dp),
                onClick = {
                    displayPopup = true
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = {
                    onEvent(UserLoginEvent.OnSignInButton)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .border(5.dp, Color.Blue, CircleShape)
            ) {
                Text(text = "S'inscrire", color = Color.Black)
            }

        }
    }
}


