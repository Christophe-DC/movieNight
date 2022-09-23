package com.cdcoding.movienight.database.data

import androidx.room.*
import com.cdcoding.movienight.database.data.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE id = :id")
    suspend fun getAccountById(id: Int): Account?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)
}