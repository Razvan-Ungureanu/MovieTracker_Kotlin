package com.example.movietracker.domain.usecase

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieById(movieId)
    }
}