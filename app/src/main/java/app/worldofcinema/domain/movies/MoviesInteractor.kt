package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.moviesfragment.Category
import javax.inject.Inject

class MoviesInteractor @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun getAllMovies() {
        moviesRepository.getAllMovies()
    }

    suspend fun showAllMovies(): List<Category> {
        return moviesRepository.showAllMovies()
    }
}