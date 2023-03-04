package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
) {

    suspend fun getMovieDetailsById(id: String): DetailsModel {
        return movieDetailsRepository.getMovieDetailsById(id)
    }
}