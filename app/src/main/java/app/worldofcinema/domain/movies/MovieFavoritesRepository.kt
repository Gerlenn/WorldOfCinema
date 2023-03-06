package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import kotlinx.coroutines.flow.Flow

interface MovieFavoritesRepository {

    suspend fun getFavorites(): Flow<List<FavoritesModel>>

    suspend fun deleteFavoriteById(id: String)

    suspend fun logoutUser()
}