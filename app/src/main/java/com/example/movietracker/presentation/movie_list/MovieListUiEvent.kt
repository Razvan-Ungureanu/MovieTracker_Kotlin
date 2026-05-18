import com.example.movietracker.domain.model.Movie

sealed interface MovieListUiEvent {
    data class QueryChanged(val query: String) : MovieListUiEvent
    data object SearchClicked : MovieListUiEvent
    data class MovieClicked(val movieId: Int) : MovieListUiEvent
    data class FavoriteClicked(val movie: Movie) : MovieListUiEvent
}