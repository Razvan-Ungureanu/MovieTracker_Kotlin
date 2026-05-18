package com.example.movietracker.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.repository.FakeMovieRepository
import com.example.movietracker.domain.model.Movie
import com.example.movietracker.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val searchMoviesUseCase = SearchMoviesUseCase(
        repository = FakeMovieRepository()
    )

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun searchMovies() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val result = searchMoviesUseCase(_uiState.value.query)

            result
                .onSuccess { movies ->
                    _uiState.update {
                        it.copy(
                            movies = movies,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            movies = emptyList(),
                            isLoading = false,
                            errorMessage = error.message ?: "Something went wrong"
                        )
                    }
                }
        }
    }

    fun toggleFavorite(movie: Movie) {
        _uiState.update { currentState ->
            currentState.copy(
                movies = currentState.movies.map {
                    if (it.id == movie.id) {
                        it.copy(isFavorite = !it.isFavorite)
                    } else {
                        it
                    }
                }
            )
        }
    }
}