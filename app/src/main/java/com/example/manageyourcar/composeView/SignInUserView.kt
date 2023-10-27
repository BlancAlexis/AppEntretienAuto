package com.example.manageyourcar.composeView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.viewmodel.UserSubscriptionEvent
import com.example.manageyourcar.composeView.UIState.SignInUiState
import com.example.manageyourcar.composeView.common.CustomTextField

@Composable
fun SignInUserView(
    uiState: SignInUiState,
    onEvent: (UserSubscriptionEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.aligned(Alignment.),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState.userLogin?.let {
            CustomTextField(
                textFieldValue = it,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(10.dp),
                label = "Identifiant",
                readOnly = false,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    onEvent(UserSubscriptionEvent.OnLoginChanged(it))
                }
            )
        }
        uiState.userPassword?.let {
            CustomTextField(
                textFieldValue = it,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(10.dp),
                label = "Mot de passe",
                readOnly = false,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    onEvent(UserSubscriptionEvent.OnPasswordChanged(it))
                }
            )
        }
        uiState.userValidatePassword?.let {
            CustomTextField(
                textFieldValue = it,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(10.dp),
                label = "Confirmation du mot de passe",
                readOnly = false,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    onEvent(UserSubscriptionEvent.OnValidatePasswordChanged(it))
                }
            )
        }
Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onEvent(UserSubscriptionEvent.OnClickSendButton) }, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(
                text = "Valider",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    SignInUserView(uiState = SignInUiState(), onEvent = {})
}