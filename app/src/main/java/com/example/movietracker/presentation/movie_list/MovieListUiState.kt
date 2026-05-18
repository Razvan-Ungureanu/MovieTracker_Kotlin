package com.example.movietracker.presentation.movie_list

import com.example.movietracker.domain.model.Movie

data class MovieListUiState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)