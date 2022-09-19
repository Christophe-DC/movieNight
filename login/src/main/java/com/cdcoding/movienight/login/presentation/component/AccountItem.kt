package com.cdcoding.movienight.login.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cdcoding.movienight.database.data.model.Account


@Composable
fun AccountItem(account: Account) {
    Column {
        Row(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colors.secondary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "${account.firstName.first().uppercase()}${
                    account.lastName.first().uppercase()
                }",
                style = typography.button.copy(fontSize = 30.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Row(
            modifier = Modifier
                .width(80.dp)
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = account.pseudo,
                color = MaterialTheme.colors.onBackground,
                style = typography.body2,
                textAlign = TextAlign.Center
            )
        }
    }
}