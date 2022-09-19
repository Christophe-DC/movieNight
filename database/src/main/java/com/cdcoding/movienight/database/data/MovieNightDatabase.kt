package com.cdcoding.movienight.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.data.model.Movie

@Database(
    entities = [
        Account::class,
        Movie::class],
    version = 1
)
internal abstract class MovieNightDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao
    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME = "movie_night_db"
    }
}