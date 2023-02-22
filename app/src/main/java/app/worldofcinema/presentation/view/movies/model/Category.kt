package app.worldofcinema.presentation.view.movies.model

import app.worldofcinema.data.model.MovieModel

data class Category(
    var Title: String,
    var Items: List<MovieModel>,
)