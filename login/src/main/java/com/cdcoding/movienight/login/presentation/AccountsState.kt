package com.cdcoding.movienight.login.presentation

import com.cdcoding.movienight.login.domain.model.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val isNewAccountModalVisible: Boolean = false,
    val isNewAccountCreating: Boolean = false,
)
