package com.example.movietracker.domain.usecase

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository

class ToggleFavoriteMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}