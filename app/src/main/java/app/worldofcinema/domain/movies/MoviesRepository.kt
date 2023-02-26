package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.Category

interface MoviesRepository {

    suspend fun getAllMovies()

    suspend fun getTopMovies()

    suspend fun getMostPopularMovies()

    suspend fun getInTheatersMovies()

    suspend fun getComingSoonMovies()

    suspend fun showAllMovies(): List<Category>
}