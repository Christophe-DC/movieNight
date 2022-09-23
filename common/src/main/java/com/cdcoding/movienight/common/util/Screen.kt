package com.cdcoding.movienight.common.util

sealed class Screen(val route: String) {
    object AccountsScreen: Screen("accounts_screen")
    object MoviesScreen: Screen("movies_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}