package com.example.movietracker.presentation.movie_detail

import com.example.movietracker.domain.model.Movie

data class MovieDetailUiState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)