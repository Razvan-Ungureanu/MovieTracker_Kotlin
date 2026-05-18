package com.example.movietracker.presentation.movie_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movietracker.domain.model.Movie

@Composable
fun MovieListScreen(
    onMovieClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: MovieListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieListContent(
        uiState = uiState,
        onQueryChanged = viewModel::onQueryChanged,
        onSearchClick = viewModel::searchMovies,
        onMovieClick = onMovieClick,
        onFavoriteClick = viewModel::toggleFavorite,
        onFavoritesClick = onFavoritesClick
    )
}

@Composable
private fun MovieListContent(
    uiState: MovieListUiState,
    onQueryChanged: (String) -> Unit,
    onSearchClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onFavoriteClick: (Movie) -> Unit,
    onFavoritesClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Movie Tracker")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.query,
                onValueChange = onQueryChanged,
                label = {
                    Text(text = "Search movie")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onSearchClick
            ) {
                Text(text = "Search")
            }

            TextButton(
                onClick = onFavoritesClick
            ) {
                Text(text = "Open favorites")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
            }

            uiState.movies.isEmpty() -> {
                Text(text = "No movies yet. Try searching for Inception.")
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.movies) { movie ->
                        MovieItem(
                            movie = movie,
                            onMovieClick = onMovieClick,
                            onFavoriteClick = onFavoriteClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    onMovieClick: (Int) -> Unit,
    onFavoriteClick: (Movie) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = movie.title)
            Text(text = movie.year)

            Button(
                onClick = {
                    onMovieClick(movie.id)
                }
            ) {
                Text(text = "Details")
            }

            TextButton(
                onClick = {
                    onFavoriteClick(movie)
                }
            ) {
                Text(
                    text = if (movie.isFavorite) {
                        "Remove favorite"
                    } else {
                        "Add favorite"
                    }
                )
            }
        }
    }
}
