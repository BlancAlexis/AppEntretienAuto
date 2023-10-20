package com.example.manageyourcar.composeView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.composeView.common.CustomTextField

@Composable
fun new_user_ui(
    onButtonClick : () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            label = "Identifiant",
            readOnly = false,
            passwordVisible = true,
            iconRight = null,
            iconLeft = null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            label = "Mot de passe",
            readOnly = false,
            passwordVisible = true,
            iconRight = null,
            iconLeft = null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = {}
        )
        CustomTextField(
            label = "Confirmation du mot de passe",
            readOnly = false,
            passwordVisible = false,
            iconRight = null,
            iconLeft = null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = {}
        )

        Button(onClick = { onButtonClick() }, modifier = Modifier.fillMaxWidth(0.5f)) {
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
    new_user_ui()
}