package com.cdcoding.movienight.movies.domain.use_case

import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.domain.repository.AccountRepository

class GetAccount(
    private val repository: AccountRepository
) {

    suspend operator fun invoke(id: Int): Account? {
        return repository.getAccountById(id)
    }
}