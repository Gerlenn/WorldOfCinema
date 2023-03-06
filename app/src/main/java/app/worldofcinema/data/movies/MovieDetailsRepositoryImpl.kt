package app.worldofcinema.data.movies

import app.worldofcinema.data.database.dao.MoviesDAO
import app.worldofcinema.data.database.entities.FavoritesEntity
import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieDetailsRepository
import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesDAO: MoviesDAO,
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsById(id: String): DetailsModel {
        return withContext(Dispatchers.IO) {
            val response = apiService.getDetailsMovieById(id)
            val details = response.body()?.let {
                DetailsModel(
                    detailsResponse = it
                )
            }
            details!!
        }
    }

    override suspend fun favoriteSelected(detailsModel: DetailsModel, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            moviesDAO.addToFavorite(
                detailsModel.id,
                isFavorite
            )

            moviesDAO.insertFavoritesEntity(
                FavoritesEntity(
                    detailsModel.id,
                    detailsModel.imDbRating,
                    detailsModel.image,
                    detailsModel.title,
                    detailsModel.year
                )
            )
        }
    }
}