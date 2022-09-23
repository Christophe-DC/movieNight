package com.cdcoding.movienight.api.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class BackdropDto(
    @SerializedName("aspect_ratio")
    val aspect_ratio: Double,
    val height: Int,
    @SerializedName("file_path")
    val file_path: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    val width: Int
)