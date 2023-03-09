package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel

interface MovieDetailsRepository {

    suspend fun getMovieDetailsById(id: String): DetailsModel

    suspend fun favoriteSelected(detailsModel: DetailsModel, isFavorite: Boolean)
}