package com.cdcoding.movienight.login.domain.use_case

import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetAccounts(
    private val repository: AccountRepository
) {

    operator fun invoke(): Flow<List<Account>> {
        return repository.getAccounts()
    }
}