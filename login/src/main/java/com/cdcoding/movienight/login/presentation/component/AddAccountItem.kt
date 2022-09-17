package com.cdcoding.movienight.login.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun AddAccountItem(
    onAddAccountClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.onSurface),
        onClick = onAddAccountClick
    )
    {
        Icon(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}