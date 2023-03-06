package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetailsById(id: String): DetailsModel

    suspend fun favoriteSelected(detailsModel: DetailsModel, isFavorite: Boolean)
}