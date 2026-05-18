package com.example.movietracker.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietracker.domain.usecase.GetMovieDetailsUseCase

class MovieDetailViewModelFactory(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val movieId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            movieId = movieId
        ) as T
    }
}