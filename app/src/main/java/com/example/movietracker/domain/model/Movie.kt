package com.example.movietracker.domain.model

import kotlinx.coroutines.flow.MutableStateFlow

data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val posterUrl: String?,
    val isFavorite: Boolean
)
