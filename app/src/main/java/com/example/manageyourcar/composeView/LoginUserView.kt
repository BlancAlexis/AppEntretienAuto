package com.example.manageyourcar.composeView

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.composeView.common.CustomDialog
import com.example.manageyourcar.composeView.common.CustomTextField

@Composable
fun LoginUserView(
    onClickAction: (String, String) -> Unit
) {
    var displayPopup by remember { mutableStateOf(false) }
    var userID by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

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
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textFieldValue = userID,
            label = "Identifiant",
            readOnly = false,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                userID = it
            }
        )
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = "Mot de passe",
            textFieldValue = userPassword,
            readOnly = false,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                userPassword = it
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { onClickAction(userID, userPassword) }) {
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
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .border(5.dp, Color.Blue, CircleShape)
        ) {
            Text(text = "S'inscrire", color = Color.Black)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialogCenterd() {
    LoginUserView(
        onClickAction = { f, d -> }
    )
}