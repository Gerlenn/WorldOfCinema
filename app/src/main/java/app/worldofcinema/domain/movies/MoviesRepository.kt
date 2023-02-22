package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.Category

interface MoviesRepository {

    suspend fun getData(): List<Category>

    suspend fun getTopMovies(): Category?

    suspend fun getMostPopularMovies(): Category?

    suspend fun getInTheatersMovies(): Category?

    suspend fun getComingSoonMovies(): Category?
}