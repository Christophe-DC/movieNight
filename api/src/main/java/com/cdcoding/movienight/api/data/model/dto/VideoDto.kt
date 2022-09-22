package com.cdcoding.movienight.api.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class VideoDto(
    val id: String,
    val key: String,
    val name: String?,
    val official: Boolean?,
    @SerializedName("published_at")
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)