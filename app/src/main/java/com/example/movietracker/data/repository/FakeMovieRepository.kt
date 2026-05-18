package com.example.movietracker.data.repository

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository
import kotlinx.coroutines.delay

class FakeMovieRepository : MovieRepository {

    override suspend fun searchMovies(query: String): Result<List<Movie>> {
        delay(800)

        val movies = listOf(
            Movie(
                id = 1,
                title = "Inception",
                year = "2010",
                posterUrl = null,
                isFavorite = false
            ),
            Movie(
                id = 2,
                title = "Interstellar",
                year = "2014",
                posterUrl = null,
                isFavorite = false
            ),
            Movie(
                id = 3,
                title = "The Dark Knight",
                year = "2008",
                posterUrl = null,
                isFavorite = false
            )
        )

        return Result.success(
            movies.filter {
                it.title.contains(query, ignoreCase = true)
            }
        )
    }
}
