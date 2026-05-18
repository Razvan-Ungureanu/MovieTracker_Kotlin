package com.example.movietracker.di

import com.example.movietracker.data.repository.FakeMovieRepository
import com.example.movietracker.domain.repository.MovieRepository
import com.example.movietracker.domain.usecase.GetFavoriteMoviesUseCase
import com.example.movietracker.domain.usecase.GetMovieDetailsUseCase
import com.example.movietracker.domain.usecase.SearchMoviesUseCase
import com.example.movietracker.domain.usecase.ToggleFavoriteMovieUseCase

object AppContainer {

    private val movieRepository: MovieRepository = FakeMovieRepository()

    val searchMoviesUseCase: SearchMoviesUseCase =
        SearchMoviesUseCase(movieRepository)

    val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase =
        ToggleFavoriteMovieUseCase(movieRepository)

    val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase =
        GetFavoriteMoviesUseCase(movieRepository)

    val getMovieDetailsUseCase: GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(movieRepository)
}