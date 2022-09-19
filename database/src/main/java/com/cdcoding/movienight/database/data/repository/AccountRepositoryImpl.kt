package com.cdcoding.movienight.database.data.repository

import com.cdcoding.movienight.database.data.AccountDao
import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

internal class AccountRepositoryImpl(
    private val dao: AccountDao
) : AccountRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return dao.getAccounts()
    }

    override suspend fun getAccountById(id: Int): Account? {
        return dao.getAccountById(id)
    }

    override suspend fun insertAccount(account: Account) {
        return dao.insertAccount(account)
    }

    override suspend fun deleteAccount(account: Account) {
        return dao.deleteAccount(account)
    }
}