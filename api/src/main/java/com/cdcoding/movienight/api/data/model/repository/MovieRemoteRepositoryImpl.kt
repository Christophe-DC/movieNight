package com.cdcoding.movienight.api.data.model.repository

import com.cdcoding.movienight.api.data.model.MovieApi
import com.cdcoding.movienight.api.data.model.dto.toMovieDetail
import com.cdcoding.movienight.api.data.model.dto.toMovies
import com.cdcoding.movienight.api.domain.repository.MovieRemoteRepository
import com.cdcoding.movienight.database.data.model.MovieDetail
import com.cdcoding.movienight.database.data.model.Movies

internal class MovieRemoteRepositoryImpl(
    private val movieApi: MovieApi
) : MovieRemoteRepository {

    override suspend fun fetchMovies(): Movies {
        return movieApi.getMovies().toMovies()
    }

    override suspend fun fetchMovieById(id: Int): MovieDetail {
        return movieApi.getMovieDetail(id).toMovieDetail()
    }

}