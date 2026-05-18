package com.example.movietracker.presentation.movie_list

import MovieListUiEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.di.AppContainer
import com.example.movietracker.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val searchMoviesUseCase = AppContainer.searchMoviesUseCase
    private val toggleFavoriteMovieUseCase = AppContainer.toggleFavoriteMovieUseCase

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            is MovieListUiEvent.QueryChanged -> {
                _uiState.update {
                    it.copy(query = event.query)
                }
            }

            MovieListUiEvent.SearchClicked -> {
                searchMovies()
            }

            is MovieListUiEvent.FavoriteClicked -> {
                toggleFavorite(event.movie)
            }

            is MovieListUiEvent.MovieClicked -> Unit
        }
    }

    private fun searchMovies() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val result = searchMoviesUseCase(_uiState.value.query)

            result
                .onSuccess { movies ->
                    _uiState.update {
                        it.copy(
                            movies = movies,
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            toggleFavoriteMovieUseCase(movie)

            _uiState.update { currentState ->
                currentState.copy(
                    movies = currentState.movies.map { currentMovie ->
                        if (currentMovie.id == movie.id) {
                            currentMovie.copy(isFavorite = !currentMovie.isFavorite)
                        } else {
                            currentMovie
                        }
                    }
                )
            }
        }
    }
}
