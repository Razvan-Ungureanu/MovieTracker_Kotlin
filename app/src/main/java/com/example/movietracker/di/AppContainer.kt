package com.example.movietracker.di

import com.example.movietracker.data.repository.FakeMovieRepository
import com.example.movietracker.domain.usecase.GetFavoriteMoviesUseCase
import com.example.movietracker.domain.usecase.SearchMoviesUseCase
import com.example.movietracker.domain.usecase.ToggleFavoriteMovieUseCase

object AppContainer {

    private val movieRepository = FakeMovieRepository()

    val searchMoviesUseCase = SearchMoviesUseCase(movieRepository)

    val toggleFavoriteMovieUseCase = ToggleFavoriteMovieUseCase(movieRepository)

    val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
}
