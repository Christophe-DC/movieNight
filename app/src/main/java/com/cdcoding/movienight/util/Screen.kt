package com.cdcoding.movienight.util

sealed class Screen(val route: String) {
    object AccountsScreen: Screen("accounts_screen")
}