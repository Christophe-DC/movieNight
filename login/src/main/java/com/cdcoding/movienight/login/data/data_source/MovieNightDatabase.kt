package com.cdcoding.movienight.login.data.data_source

import androidx.room.Database
import com.cdcoding.movienight.login.domain.model.Account

@Database(
    entities = [Account::class],
    version = 1
)
abstract class MovieNightDatabase {

    abstract val accountDao: AccountDao
}