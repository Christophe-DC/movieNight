package com.cdcoding.movienight.login.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cdcoding.movienight.database.data.model.Account


@Composable
fun AccountItem(
    account: Account,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    onAccountItemClick: (account: Account) -> Unit
) {
    Column(
        modifier = Modifier.alpha(
            if (!isEnabled && !isLoading) 0.4f
            else 1f
        )
    ) {
        Row(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(shape = CircleShape),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                onClick = { if (isEnabled) onAccountItemClick(account) }
            )
            {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = "${account.firstName.first().uppercase()}${
                            account.lastName.first().uppercase()
                        }",
                        style = typography.button.copy(fontSize = 30.sp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
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