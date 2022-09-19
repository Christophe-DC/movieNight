package com.cdcoding.movienight.api.data.model

import com.cdcoding.movienight.api.BuildConfig
import com.cdcoding.movienight.api.data.model.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MovieApi {

    @GET("trending/{media_type}/{time_window}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovies(
        @Path("media_type") mediaType: String = "movie",
        @Path("time_window") timeWindow: String = "day"
    ): MoviesDto
}
