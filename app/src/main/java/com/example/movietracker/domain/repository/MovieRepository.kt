package com.example.movietracker.domain.repository

import com.example.movietracker.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun getMovieById(movieId: Int): Result<Movie>

    suspend fun toggleFavorite(movie: Movie)

    fun getFavoriteMovies(): Flow<List<Movie>>
}