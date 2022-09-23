package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.database.data.model.Account
import kotlinx.coroutines.launch


@Composable
fun AccountGrid(
    modifier: Modifier = Modifier,
    delayMillis: Int = 1000,
    durationMillis: Int = 1000,
    accounts: List<Account>,
    accountLoading: Account? = null,
    onAddAccountClick: () -> Unit,
    onAccountItemClick: (account: Account) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {

        var runAnimation by rememberSaveable { mutableStateOf(true) }
        val offsetY = remember {
            Animatable(
                if (runAnimation) 250f
                else 50f
            )
        }
        val alpha = remember {
            Animatable(
                if (runAnimation) 0f
                else 1f
            )
        }
        LaunchedEffect(runAnimation) {
            launch {
                if (runAnimation) {
                    offsetY.animateTo(
                        targetValue = 50f,
                        animationSpec = tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis
                        )
                    )
                }
            }
            launch {
                if (runAnimation) {
                    alpha.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis
                        )
                    )
                    runAnimation = false
                }
            }
        }

        val accountsIsEven = accounts.size % 2 == 0
        val lastItemSpan = if (accounts.isEmpty()) 2 else 1
        LazyVerticalGrid(
            modifier = Modifier
                .alpha(alpha.value)
                .padding(top = offsetY.value.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(accounts) { index, account ->
                val alignment = if (index % 2 == 0) Alignment.CenterEnd else Alignment.CenterStart
                Box(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    contentAlignment = alignment,
                    propagateMinConstraints = false
                ) {
                    AccountItem(
                        account = account,
                        isLoading = account.id == accountLoading?.id,
                        isEnabled = accountLoading == null,
                        onAccountItemClick = onAccountItemClick
                    )
                }
            }
            item(span = { GridItemSpan(lastItemSpan) }) {
                val alignment = if (accounts.isEmpty()) {
                    Alignment.Center
                } else {
                    if (accountsIsEven) Alignment.CenterEnd else Alignment.CenterStart
                }
                Box(
                    modifier = Modifier,
                    contentAlignment = alignment,
                    propagateMinConstraints = false
                ) {
                    AddAccountItem(
                        isEnabled = accountLoading == null,
                        onAddAccountClick = onAddAccountClick
                    )
                }
            }
        }
    }
}