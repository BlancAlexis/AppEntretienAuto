package com.example.manageyourcar.UIlayer.view.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomTextField(
    enabled: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    textFieldValue: String,
    label: String = "",
    onValueChange: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    lines: Int = 1,
    error: String? = null,
    placeholder: String? = null,
    textStyle: TextStyle = TextStyle.Default,
    labelTextStyle: TextStyle = TextStyle.Default,
) {
    Column(
        modifier = modifier,
    ) {
        var localVisualTransformation by remember {
            mutableStateOf(visualTransformation)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(label, style = labelTextStyle)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            border = if (!error.isNullOrBlank()) BorderStroke(2.dp, Color.Red) else null,
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .padding(12.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    BasicTextField(

                        enabled = enabled,
                        readOnly = readOnly,
                        value = textFieldValue,
                        onValueChange = onValueChange,
                        maxLines = lines,
                        singleLine = lines == 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = imeAction,
                            keyboardType = keyboardType
                        ),
                        keyboardActions = keyboardActions,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ),
                        visualTransformation = localVisualTransformation,
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth()
                    )
                    if (placeholder != null && textFieldValue.isBlank()) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = Color.LightGray
                        )
                    }
                }
            }
            error?.let {
                if (it.isNotBlank()) {
                    Text(
                        it,
                        color = Color.Red,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomDialogCenterd() {
    CustomTextField(
        keyboardType = KeyboardType.Password,
        label = "password",
        textFieldValue = "",
        error = ""
    )
}