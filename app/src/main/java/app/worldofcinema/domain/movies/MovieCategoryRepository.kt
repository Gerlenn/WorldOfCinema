package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

interface MovieCategoryRepository {

    suspend fun showCategoryMovies(categoryTitle: String): List<MoviesModel>
}