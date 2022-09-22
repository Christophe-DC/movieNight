package com.cdcoding.movienight.login.presentation

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cdcoding.movienight.common.util.Screen
import com.cdcoding.movienight.login.R
import com.cdcoding.movienight.login.presentation.component.AccountGrid
import com.cdcoding.movienight.login.presentation.component.AddAccountDialog
import com.cdcoding.movienight.login.presentation.component.AppTitle
import com.cdcoding.movienight.login.presentation.component.BackgroundContainer
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AccountsScreen(
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    val delayMillis = 1000
    val durationMillis = 1000
    val accounts = viewModel.state.value.accounts
    val firstNameState = viewModel.accountFirstName.value
    val lastNameState = viewModel.accountLastName.value
    val pseudoState = viewModel.accountPseudo.value
    val accountLoading = viewModel.state.value.accountLoading

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AccountsViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(mContext, event.message, Toast.LENGTH_LONG).show()
                }
                is AccountsViewModel.UiEvent.MoviesFetched -> {
                }
            }
        }
    }

    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(alpha) {
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
                .fillMaxSize()
                .systemBarsPadding(),
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
            accountLoading = accountLoading,
            onAddAccountClick = { viewModel.onEvent(AccountsEvent.OpenNewAccountModal) },
            onAccountItemClick = { account ->
                viewModel.onEvent(AccountsEvent.OpenAccount(account))
            }
        )
    }
    if (viewModel.state.value.isNewAccountModalVisible) {
        AddAccountDialog(
            onDismissRequest = { viewModel.onEvent(AccountsEvent.CloseNewAccountModal) },
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