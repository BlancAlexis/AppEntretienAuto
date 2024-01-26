package com.example.manageyourcar.UIlayer.view.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun InformationRow(
    title1: String,
    content1: String,
    icon1: Painter,
    title2: String,
    content2: String,
    icon2: Painter,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        InformationBox(
            title = title1,
            content = content1,
            icon = icon1,
        )
        Box(
            Modifier.width(25.dp)
        ) {
        }
        InformationBox(
            title = title2,
            content = content2,
            icon = icon2,
        )
    }
}