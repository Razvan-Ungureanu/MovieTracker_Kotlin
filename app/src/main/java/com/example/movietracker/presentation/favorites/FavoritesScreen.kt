package com.example.movietracker.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorites Screen")

        Button(
            onClick = onBackClick
        ) {
            Text(text = "Back")
        }

        if (uiState.isLoading) {
            Text(text = "Loading...")
        }

        if (uiState.movies.isEmpty() && !uiState.isLoading) {
            Text(text = "No favorite movies yet.")
        }

        uiState.movies.forEach { movie ->
            Card(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = movie.title)
                    Text(text = movie.year)
                }
            }
        }
    }
}