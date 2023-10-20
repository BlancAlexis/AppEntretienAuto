package com.example.manageyourcar.composeView.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    onValueChanged : () -> Unit,
    passwordVisible: Boolean = true,
    readOnly: Boolean = true,
    keyboardOptions: KeyboardOptions? = null,
    iconLeft: ImageVector? = null,
    iconRight: ImageVector? = null
) {
    var text by remember {
        mutableStateOf("")
    }
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Row(
            Modifier
                .height(IntrinsicSize.Min)
        ) {
            if (iconLeft != null) {
                Icon(
                    imageVector = iconLeft,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                Modifier
                    .weight(1f)
                    .padding(8.dp),
                readOnly = readOnly,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Box(
                Modifier
                    .padding(vertical = 2.dp)
                    .width(1.dp)
                    .fillMaxHeight()
                // .background(MaterialTheme.colors.onBackground.copy(alpha = .5f))
            )
            if (iconRight != null) {
                Icon(
                    imageVector = iconRight,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialogCenterd() {
    CustomTextField(
        passwordVisible = true,
        iconLeft = Icons.Default.Search,
        iconRight = Icons.Default.Settings,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChanged = {}
    )
}