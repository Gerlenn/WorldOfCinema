package app.worldofcinema.presentation.view.movies.model.favoritesfragment

import app.worldofcinema.data.database.entities.MoviesEntity

class FavoritesModel(
    val id: String,
    val imDbRating: String?,
    val image: String,
    val title: String,
    val year: String,
) {
    constructor(movieEntity: MoviesEntity) : this(
        movieEntity.id,
        movieEntity.imDbRating,
        movieEntity.image,
        movieEntity.title,
        movieEntity.year)
}