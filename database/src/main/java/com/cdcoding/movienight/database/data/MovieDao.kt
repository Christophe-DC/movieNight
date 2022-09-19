package com.cdcoding.movienight.database.data

import androidx.room.*
import com.cdcoding.movienight.database.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}