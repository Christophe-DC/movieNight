package com.cdcoding.movienight.database.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity
data class MovieDetail(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<String>?,
    val homepage: String?,
    @PrimaryKey val id: Int? = null,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val videoUrl: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val backdropsPath: List<String>?
)

class ListConverter {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}