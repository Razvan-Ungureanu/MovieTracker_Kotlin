package com.example.movietracker.data.repository

import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeMovieRepository : MovieRepository {

    private val movies = MutableStateFlow(
        listOf(
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
            ),
            Movie(
                id = 4,
                title = "Tenet",
                year = "2020",
                posterUrl = null,
                isFavorite = false
            )
        )
    )

    override suspend fun searchMovies(query: String): Result<List<Movie>> {
        delay(500)

        val filteredMovies = movies.value.filter {
            it.title.contains(query, ignoreCase = true)
        }

        return Result.success(filteredMovies)
    }

    override suspend fun toggleFavorite(movie: Movie) {
        movies.value = movies.value.map { currentMovie ->
            if (currentMovie.id == movie.id) {
                currentMovie.copy(isFavorite = !currentMovie.isFavorite)
            } else {
                currentMovie
            }
        }
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movies.map { movieList ->
            movieList.filter { movie ->
                movie.isFavorite
            }
        }
    }
}