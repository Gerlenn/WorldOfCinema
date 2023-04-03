package app.worldofcinema.presentation.view.movies.model.detailsfragment

data class DetailsModel(
    val awards: String?,
    val countries: String,
    val id: String,
    val imDbRating: String?,
    val image: String?,
    val plot: String,
    val stars: String,
    val genres: String,
    val title: String,
    val year: String,
    val linkEmbed: String?,
    val thumbnailUrl: String?,
    val actor: List<Actor>,
    val items: List<LinkImg>?,
    val isFavorite: Boolean? = false,
)