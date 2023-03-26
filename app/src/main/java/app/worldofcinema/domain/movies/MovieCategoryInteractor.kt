package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import javax.inject.Inject

class MovieCategoryInteractor @Inject constructor(
    private val movieCategoryRepository: MovieCategoryRepository
){
    suspend fun showCategoryMovies(categoryTitle: String): List<MoviesModel> {
        return movieCategoryRepository.showCategoryMovies(categoryTitle)
    }
}