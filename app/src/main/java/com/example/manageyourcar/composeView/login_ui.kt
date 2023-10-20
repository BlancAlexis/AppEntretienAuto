package com.example.manageyourcar.composeView

import android.service.autofill.OnClickAction
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.manageyourcar.composeView.common.CustomTextField

@Composable
fun LoginUserView(
    onClickAction: () -> Unit) {
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
            label = "Identifiant",
            readOnly = false,
            passwordVisible = true,
            iconRight = null,
            iconLeft = null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = { }
        )
        CustomTextField(
            label = "Mot de passe",
            readOnly = false,
            passwordVisible = false,
            iconRight = null,
            iconLeft = null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = {}
        )

        Button(onClick = { }) {
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
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { onClickAction() },
            modifier = Modifier.fillMaxWidth(0.5f).border(5.dp, Color.Green, CircleShape)
        ) {
            Text(text = "Rechercher", color = Color.Black)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialogCenterd() {
    LoginUserView()
}