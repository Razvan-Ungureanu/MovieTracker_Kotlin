package com.example.movietracker.domain.usecase

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository

class SearchMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(query: String): Result<List<Movie>> {
        if (query.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Search query cannot be empty")
            )
        }

        return repository.searchMovies(query.trim())
    }
}