package com.cdcoding.movienight.login.presentation

import com.cdcoding.movienight.database.data.model.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val isNewAccountModalVisible: Boolean = false,
    val isNewAccountCreating: Boolean = false,
    val accountLoading: Account? = null,
)
