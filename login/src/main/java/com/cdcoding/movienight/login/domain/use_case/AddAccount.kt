package com.cdcoding.movienight.login.domain.use_case

import com.cdcoding.movienight.login.domain.model.Account
import com.cdcoding.movienight.login.domain.model.InvalidAccountException
import com.cdcoding.movienight.login.domain.repository.AccountRepository

class AddAccount(
    private val repository: AccountRepository
) {

    @Throws(InvalidAccountException::class)
    suspend operator fun invoke(account: Account) {
        if(account.firstName.isBlank()) {
            throw InvalidAccountException("The first name of the account can't be empty")
        }
        if(account.lastName.isBlank()) {
            throw InvalidAccountException("The last name of the account can't be empty")
        }
        if(account.pseudo.isBlank()) {
            throw InvalidAccountException("The pseudo of the account can't be empty")
        }
        repository.insertAccount(account)
    }
}