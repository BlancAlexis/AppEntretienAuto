package com.example.manageyourcar.UIlayer.view.fragments.AddUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.SignInUiState
import com.example.manageyourcar.UIlayer.view.common.CustomDialog
import com.example.manageyourcar.UIlayer.view.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.UserSubscriptionEvent

@Composable
fun AddUserView(
    uiState: SignInUiState,
    onEvent: (UserSubscriptionEvent) -> Unit = {}
) {

    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )

    if (uiState.onInternetLost) {
        CustomDialog(title = "Internet perdu")
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0, 97, 162))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    tint = Color(0, 29, 54),
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    fontFamily = juraFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                    text = "Créer votre compte",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),

                    )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                uiState.userLogin?.let {
                    CustomTextField(
                        textFieldValue = it,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(10.dp)
                            .background(
                                color = Color(54, 52, 59),
                                shape = RoundedCornerShape(6.dp)
                            ),
                        placeholder = "Identifiant",
                        readOnly = false,
                        keyboardType = KeyboardType.Text,
                        onValueChange = {
                            onEvent(UserSubscriptionEvent.OnLoginChanged(it))
                        }
                    )
                }
                uiState?.userFirstName?.let {
                    CustomTextField(
                        textFieldValue = it,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(10.dp)
                            .background(
                                color = Color(54, 52, 59),
                                shape = RoundedCornerShape(6.dp)
                            ),
                        placeholder = "Firstname",
                        readOnly = false,
                        keyboardType = KeyboardType.Text,
                        onValueChange = {
                            onEvent(UserSubscriptionEvent.OnFirstnameChanged(it))
                        }
                    )
                }
                uiState.userLastName?.let {
                    CustomTextField(
                        textFieldValue = it,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(10.dp)
                            .background(
                                color = Color(54, 52, 59),
                                shape = RoundedCornerShape(6.dp)
                            ),
                        placeholder = "Lastname",
                        readOnly = false,
                        keyboardType = KeyboardType.Text,
                        onValueChange = {
                            onEvent(UserSubscriptionEvent.OnLastNameChanged(it))
                        }
                    )
                }
                uiState.userPassword?.let {
                    CustomTextField(
                        visualTransformation = PasswordVisualTransformation(),
                        textFieldValue = it,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(10.dp)
                            .background(
                                color = Color(54, 52, 59),
                                shape = RoundedCornerShape(6.dp)
                            ),
                        placeholder = "Mot de passe",
                        readOnly = false,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            onEvent(UserSubscriptionEvent.OnPasswordChanged(it))
                        }
                    )
                }
                uiState.userValidatePassword?.let {
                    CustomTextField(
                        visualTransformation = PasswordVisualTransformation(),
                        textFieldValue = it,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(10.dp)
                            .background(
                                color = Color(54, 52, 59),
                                shape = RoundedCornerShape(6.dp)
                            ),
                        placeholder = "Confirmation du mot de passe",
                        readOnly = false,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            onEvent(UserSubscriptionEvent.OnConfirmPasswordChanged(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onEvent(UserSubscriptionEvent.OnClickSendButton) },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(
                        text = "Valider",
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    AddUserView(uiState = SignInUiState(), onEvent = {})
}