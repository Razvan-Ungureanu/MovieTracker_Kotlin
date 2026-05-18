package com.example.movietracker.presentation.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movietracker.di.AppContainer

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieDetailViewModel = viewModel(
        factory = MovieDetailViewModelFactory(
            getMovieDetailsUseCase = AppContainer.getMovieDetailsUseCase,
            movieId = movieId
        )
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieDetailContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@Composable
private fun MovieDetailContent(
    uiState: MovieDetailUiState,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onBackClick) {
                    Text(text = "Back")
                }
            }

            uiState.movie != null -> {
                Text(text = "Movie Detail Screen")
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Title: ${uiState.movie.title}")
                Text(text = "Year: ${uiState.movie.year}")
                Text(text = "Favorite: ${uiState.movie.isFavorite}")

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = onBackClick) {
                    Text(text = "Back")
                }
            }
        }
    }
}