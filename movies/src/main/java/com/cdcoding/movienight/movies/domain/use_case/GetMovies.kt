package com.cdcoding.movienight.movies.domain.use_case

import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.database.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}