package com.cdcoding.movienight.api.data.model

import androidx.compose.ui.text.intl.Locale
import com.cdcoding.movienight.api.BuildConfig
import com.cdcoding.movienight.api.data.model.dto.MovieDetailDto
import com.cdcoding.movienight.api.data.model.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieApi {

    @GET("trending/{media_type}/{time_window}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovies(
        @Path("media_type") mediaType: String = "movie",
        @Path("time_window") timeWindow: String = "day"
    ): MoviesDto

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "${Locale.current.language}-${Locale.current.region}",
        @Query("append_to_response") responseType: String = "videos"
    ): MovieDetailDto
}
