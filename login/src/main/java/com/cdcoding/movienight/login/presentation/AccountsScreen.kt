package com.cdcoding.movienight.login.presentation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cdcoding.movienight.login.R
import com.cdcoding.movienight.login.presentation.component.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountsScreen(
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val delayMillis = 1000
    val durationMillis = 1000
    val accounts = viewModel.state.value.accounts
    val firstNameState = viewModel.accountFirstName.value
    val lastNameState = viewModel.accountLastName.value
    val pseudoState = viewModel.accountPseudo.value

    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundContainer(
            delayMillis = delayMillis,
            durationMillis = durationMillis
        )
        AppTitle(
            modifier = Modifier
                .fillMaxSize(),
            delayMillis = delayMillis,
            durationMillis = durationMillis
        )
        val welcomeTextId = if (accounts.isNotEmpty()) R.string.welcome_message
        else R.string.first_visit
        Text(
            modifier = Modifier
                .alpha(alpha.value)
                .align(Alignment.TopCenter)
                .padding(top = 180.dp),
            text = stringResource(id = welcomeTextId),
            style = typography.h2,
            color = MaterialTheme.colors.onBackground
        )
    }
    Box {
        AccountGrid(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxHeight(),
            delayMillis = delayMillis,
            durationMillis = durationMillis,
            accounts = accounts,
            onAddAccountClick = { viewModel.onEvent(AccountsEvent.OpenNewAccountModal) }
        )
    }
    if (viewModel.state.value.isNewAccountModalVisible) {
        AddAccountDialog(
            onDismissRequest = { viewModel.onEvent(AccountsEvent.CloseNewAccountModal)},
            firstNameState = firstNameState,
            onFirstNameChange = { viewModel.onEvent(AccountsEvent.EnteredFirstName(it)) },
            onFirstNameFocusChange = { viewModel.onEvent(AccountsEvent.ChangeFirstNameFocus(it)) },
            lastNameState = lastNameState,
            onLastNameChange = { viewModel.onEvent(AccountsEvent.EnteredLastName(it)) },
            onLastNameFocusChange = { viewModel.onEvent(AccountsEvent.ChangeLastNameFocus(it)) },
            pseudoState = pseudoState,
            onPseudoChange = { viewModel.onEvent(AccountsEvent.EnteredPseudo(it)) },
            onPseudoFocusChange = { viewModel.onEvent(AccountsEvent.ChangePseudoFocus(it)) },
            onValidClick = { viewModel.onEvent(AccountsEvent.AddAccount) },
            isNewAccountCreating = viewModel.state.value.isNewAccountCreating
        )
    }
}