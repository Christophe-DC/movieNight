package com.cdcoding.movienight.login.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val firstName: String,
    val lastName: String,
    val pseudo: String,
    @PrimaryKey val id: Int? = null
)

class InvalidAccountException(message: String): Exception(message)