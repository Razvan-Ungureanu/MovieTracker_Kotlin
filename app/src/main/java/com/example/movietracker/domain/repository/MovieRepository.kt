package com.example.movietracker.domain.repository

import com.example.movietracker.domain.model.Movie

interface MovieRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>
}