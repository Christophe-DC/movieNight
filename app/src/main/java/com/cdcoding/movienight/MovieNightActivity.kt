package com.cdcoding.movienight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cdcoding.movienight.login.presentation.AccountsScreen
import com.cdcoding.movienight.movies.presentation.MoviesScreen
import com.cdcoding.movienight.ui.theme.MovieNightTheme
import com.cdcoding.movienight.common.util.Screen
import com.cdcoding.movienight.moviedetail.presentation.MovieDetailScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieNightActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MovieNightTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.AccountsScreen.route
                    ) {
                        composable(
                            route = Screen.AccountsScreen.route,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            popEnterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            },
                            popExitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            }
                        )
                        {
                            AccountsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MoviesScreen.route +
                                    "?accountId={accountId}",
                            arguments = listOf(
                                navArgument(
                                    name = "accountId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            ),
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            popEnterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            },
                            popExitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            }
                        ) {
                            MoviesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MovieDetailScreen.route +
                                    "?movieDetailId={movieDetailId}",
                            arguments = listOf(
                                navArgument(
                                    name = "movieDetailId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            ),
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            popEnterTransition = {
                                fadeIn(
                                    animationSpec = tween(2000)
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            },
                            popExitTransition = {
                                fadeOut(
                                    animationSpec = tween(2000)
                                )
                            }
                        ) {
                            MovieDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
