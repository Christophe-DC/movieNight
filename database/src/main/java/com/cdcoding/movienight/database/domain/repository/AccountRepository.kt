package com.cdcoding.movienight.database.domain.repository

import com.cdcoding.movienight.database.data.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAccounts(): Flow<List<Account>>

    suspend fun getAccountById(id: Int): Account?

    suspend fun insertAccount(account: Account)

    suspend fun deleteAccount(account: Account)
}