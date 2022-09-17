package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.login.domain.model.Account
import kotlinx.coroutines.launch


@Composable
fun AccountGrid(
    modifier: Modifier = Modifier,
    delayMillis: Int = 1000,
    durationMillis: Int = 1000,
    accounts: List<Account>,
    onAddAccountClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        val offsetY = remember {
            Animatable(250f)
        }
        val alpha = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            launch {
                offsetY.animateTo(
                    targetValue = 50f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
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
                    AccountItem(account)
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
                    AddAccountItem(onAddAccountClick)
                }
            }
        }
    }
}