package com.example.movietracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movietracker.presentation.favorites.FavoritesScreen
import com.example.movietracker.presentation.favorites.FavoritesViewModel
import com.example.movietracker.presentation.movie_detail.MovieDetailScreen
import com.example.movietracker.presentation.movie_list.MovieListScreen
import com.example.movietracker.presentation.movie_list.MovieListViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    val movieListViewModel = remember {
        MovieListViewModel()
    }

    val favoritesViewModel = remember {
        FavoritesViewModel()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.MovieList.route
    ) {
        composable(route = Screen.MovieList.route) {
            MovieListScreen(
                viewModel = movieListViewModel,
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable

            MovieDetailScreen(
                movieId = movieId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}