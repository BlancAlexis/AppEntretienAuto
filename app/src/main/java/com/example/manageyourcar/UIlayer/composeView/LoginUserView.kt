package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.LoginUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.ViewCarDetailsUIState
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.UserLoginEvent
import com.example.manageyourcar.dataLayer.model.Car
import java.util.Calendar

@Composable
fun LoginUserView(
    onEvent: (UserLoginEvent) -> Unit = {},
    uiState: LoginUiState
) {
    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )
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
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color(0, 97, 162)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Connexion",
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
                    error = uiState.userLoginError ?: "",
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .background(Color.Transparent),
                    textFieldValue = it,
                    placeholder = "Identifiant",
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
                    modifier = Modifier
                        .fillMaxWidth(0.95f),
                    placeholder = "Mot de passe",
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
                colors = ButtonDefaults.buttonColors(containerColor = Color(209, 228, 255))
            ) {
                Text(
                    text = "Connexion",
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
                    append("Forgot your password?")
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
                border = BorderStroke(5.dp,Color(209, 228, 255)),
                shape = CircleShape,
                onClick = {
                    onEvent(UserLoginEvent.OnSignInButton)
                },
                modifier = Modifier
                    .width(245.dp)
            ) {
                Text(
                    text = "Créer un compte",
                    color = Color.White,
                    fontFamily = juraFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginView() {
    LoginUserView(uiState = LoginUiState())
}