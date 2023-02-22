package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.Category
import javax.inject.Inject

class MoviesInteractor @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun getData(): List<Category> {
        return moviesRepository.getData()
    }
}