package app.worldofcinema.presentation.view.movies.model.detailsfragment

import app.worldofcinema.data.model.detailsfragment.DetailsResponse

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
) {
    constructor(detailsResponse: DetailsResponse) : this(
        detailsResponse.awards,
        detailsResponse.countries,
        detailsResponse.id,
        detailsResponse.imDbRating,
        detailsResponse.image,
        detailsResponse.plot,
        detailsResponse.stars,
        detailsResponse.genres,
        detailsResponse.title,
        detailsResponse.year,
        detailsResponse.trailer?.linkEmbed,
        detailsResponse.trailer?.thumbnailUrl
    )
}