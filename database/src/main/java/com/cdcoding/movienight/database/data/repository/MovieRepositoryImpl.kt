package com.cdcoding.movienight.database.data.repository

import com.cdcoding.movienight.database.data.MovieDao
import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.database.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

internal class MovieRepositoryImpl(
    val dao: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return dao.getMovieById(id)
    }

    override suspend fun insertMovie(movie: Movie) {
        return dao.insertMovie(movie)
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        return dao.insertMovies(movies)
    }

    override suspend fun deleteMovie(movie: Movie) {
        return dao.deleteMovie(movie)
    }
}