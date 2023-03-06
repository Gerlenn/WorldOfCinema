package app.worldofcinema.data.movies

import app.worldofcinema.data.database.dao.MoviesDAO
import app.worldofcinema.data.sharedpreferences.SharedPreferencesHelper
import app.worldofcinema.domain.movies.MovieFavoritesRepository
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieFavoritesRepositoryImpl @Inject constructor(
    private val moviesDAO: MoviesDAO,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
) : MovieFavoritesRepository {

    override suspend fun getFavorites(): Flow<List<FavoritesModel>> {
        return withContext(Dispatchers.IO) {
            val favoritesEntity = moviesDAO.getFavoritesEntities()
            favoritesEntity.map { favoritesList ->
                favoritesList.map {
                    FavoritesModel(
                        it.id,
                        it.imDbRating,
                        it.image.toString(),
                        it.title,
                        it.year
                    )
                }
            }
        }
    }

    override suspend fun deleteFavoriteById(id: String) {
        withContext(Dispatchers.IO) {
            moviesDAO.deleteFavoriteEntityById(id)
        }
    }

    override suspend fun logoutUser() {
        withContext(Dispatchers.IO) {
            sharedPreferencesHelper.removeUser()
        }
    }
}