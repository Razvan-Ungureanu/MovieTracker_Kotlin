package com.example.movietracker.domain.usecase

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return repository.getFavoriteMovies()
    }
}