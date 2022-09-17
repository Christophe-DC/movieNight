package com.cdcoding.movienight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cdcoding.movienight.ui.theme.MovieNightTheme
import com.cdcoding.movienight.util.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieNightActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MovieNightTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AccountsScreen.route
                ) {
                    composable(route = Screen.AccountsScreen.route) {
                        com.cdcoding.movienight.login.presentation.AccountsScreen(navController = navController)
                    }
                }
            }
        }
    }
}
