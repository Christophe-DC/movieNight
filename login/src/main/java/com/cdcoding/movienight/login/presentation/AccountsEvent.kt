package com.cdcoding.movienight.login.presentation

import androidx.compose.ui.focus.FocusState

sealed class AccountsEvent {
    object AddAccount: AccountsEvent()
    object OpenNewAccountModal: AccountsEvent()
    object CloseNewAccountModal: AccountsEvent()
    data class  EnteredFirstName(val value: String): AccountsEvent()
    data class  ChangeFirstNameFocus(val focusState: FocusState): AccountsEvent()
    data class  EnteredLastName(val value: String): AccountsEvent()
    data class  ChangeLastNameFocus(val focusState: FocusState): AccountsEvent()
    data class  EnteredPseudo(val value: String): AccountsEvent()
    data class  ChangePseudoFocus(val focusState: FocusState): AccountsEvent()
}

