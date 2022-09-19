package com.cdcoding.movienight.database.domain.repository

import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.database.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<Movie>>

    suspend fun getMovieById(id: Int): Movie?

    suspend fun insertMovie(movie: Movie)

    suspend fun insertMovies(movies: List<Movie>)

    suspend fun deleteMovie(movie: Movie)
}