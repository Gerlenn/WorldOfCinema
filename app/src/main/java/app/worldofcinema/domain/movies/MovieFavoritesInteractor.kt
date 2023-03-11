package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoritesInteractor @Inject constructor(
    private val movieFavoritesRepository: MovieFavoritesRepository
) {

    suspend fun getFavorites(): Flow<List<FavoritesModel>> {
        return movieFavoritesRepository.getFavorites()
    }

    suspend fun deleteFavItemById(id: String) {
        movieFavoritesRepository.deleteFavoriteById(id)
    }

    suspend fun logoutUser(){
        movieFavoritesRepository.logoutUser()
    }
}