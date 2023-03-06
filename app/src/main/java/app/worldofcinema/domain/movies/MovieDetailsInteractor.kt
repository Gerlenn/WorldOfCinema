package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
) {

    suspend fun getMovieDetailsById(id: String): DetailsModel {
        return movieDetailsRepository.getMovieDetailsById(id)
    }

    suspend fun favoriteSelected(id: String, isFavorite: Boolean){
        val foundMovie = movieDetailsRepository.getMovieDetailsById(id)
        movieDetailsRepository.favoriteSelected(foundMovie, isFavorite)
    }
}