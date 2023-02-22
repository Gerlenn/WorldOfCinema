package app.worldofcinema.data.model

data class MoviesResponse(
    val items: List<MovieModel>,
    val errorMessage: String,
)