package com.cdcoding.movienight.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.data.model.ListConverter
import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.database.data.model.MovieDetail

@Database(
    entities = [
        Account::class,
        Movie::class,
        MovieDetail::class],
    version = 1
)
@TypeConverters(ListConverter::class)
internal abstract class MovieNightDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao
    abstract val movieDao: MovieDao
    abstract val movieDetailDao: MovieDetailDao

    companion object {
        const val DATABASE_NAME = "movie_night_db"
    }
}