package com.cdcoding.movienight.login.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cdcoding.movienight.login.domain.model.Account

@Database(
    entities = [Account::class],
    version = 1
)
abstract class MovieNightDatabase: RoomDatabase() {

    abstract val accountDao: AccountDao

    companion object {
        const val DATABASE_NAME= "movie_night_db"
    }
}