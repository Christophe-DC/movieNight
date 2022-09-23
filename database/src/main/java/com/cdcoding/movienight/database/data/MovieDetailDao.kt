package com.cdcoding.movienight.database.data

import androidx.room.*
import com.cdcoding.movienight.database.data.model.MovieDetail

@Dao
internal interface MovieDetailDao {
    @Query("SELECT * FROM MovieDetail WHERE id = :id")
    suspend fun getMovieDetailById(id: Int): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    @Delete
    suspend fun deleteMovieDetail(movieDetail: MovieDetail)
}