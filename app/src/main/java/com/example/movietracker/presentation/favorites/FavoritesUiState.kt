package com.example.movietracker.presentation.favorites

import com.example.movietracker.domain.model.Movie

data class FavoritesUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)