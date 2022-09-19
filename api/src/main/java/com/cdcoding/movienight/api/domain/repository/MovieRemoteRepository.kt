package com.cdcoding.movienight.api.domain.repository

import com.cdcoding.movienight.database.data.model.Movies

interface MovieRemoteRepository {

    suspend fun fetchMovies(): Movies

}