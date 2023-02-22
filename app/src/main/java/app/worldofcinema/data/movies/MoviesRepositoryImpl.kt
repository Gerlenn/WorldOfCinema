package app.worldofcinema.data.movies

import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MoviesRepository
import app.worldofcinema.presentation.view.movies.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MoviesRepository {

    override suspend fun getData(): List<Category> {
        return withContext(Dispatchers.IO) {
            val allCategoriesMoviesList = mutableListOf<Category>()

            getTopMovies()?.let { listTopMovies ->
                allCategoriesMoviesList.add(listTopMovies)
            }

            getMostPopularMovies()?.let { listMostPopularMovies ->
                allCategoriesMoviesList.add(listMostPopularMovies)
            }

            getInTheatersMovies()?.let { listInTheatersMovies ->
                allCategoriesMoviesList.add(listInTheatersMovies)
            }

            getComingSoonMovies()?.let { listComingSoonMovies ->
                allCategoriesMoviesList.add(listComingSoonMovies)
            }
            allCategoriesMoviesList
        }
    }

    override suspend fun getTopMovies(): Category? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getTopMovies()
            response.body()?.items?.let { listTopMovies ->
                Category(
                    "Top Movies",
                    listTopMovies
                )
            }
        }
    }

    override suspend fun getMostPopularMovies(): Category? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getMostPopularMovies()
            response.body()?.items?.let { listMostPopularMovies ->
                Category(
                    "Most Popular Movies",
                    listMostPopularMovies
                )
            }
        }
    }

    override suspend fun getInTheatersMovies(): Category? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getInTheatersMovies()
            response.body()?.items?.let { listInTheatersMovies ->
                Category(
                    "In Theaters Movies",
                    listInTheatersMovies
                )
            }
        }
    }

    override suspend fun getComingSoonMovies(): Category? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getComingSoonMovies()
            response.body()?.items?.let { listComingSoonMovies ->
                Category(
                    "Coming Soon Movies",
                    listComingSoonMovies
                )
            }
        }
    }
}