package com.cdcoding.movienight.api.data.model.dto

import com.cdcoding.movienight.database.data.model.Movies
import com.google.gson.annotations.SerializedName

internal data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

internal fun MoviesDto.toMovies(): Movies {
    return Movies(
        page = page,
        results = results.map { it.toMovie() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}