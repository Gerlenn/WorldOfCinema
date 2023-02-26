package app.worldofcinema.data.movies

import app.worldofcinema.data.database.dao.MoviesDAO
import app.worldofcinema.data.database.entities.CategoriesEntity
import app.worldofcinema.data.database.entities.MoviesEntity
import app.worldofcinema.data.model.MoviesResponse
import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MoviesRepository
import app.worldofcinema.presentation.view.movies.model.Category
import app.worldofcinema.presentation.view.movies.model.MoviesModel
import app.worldofcinema.utils.AppConstants.COMING_SOON_CATEGORY_ID
import app.worldofcinema.utils.AppConstants.COMING_SOON_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.IN_THEATERS_CATEGORY_ID
import app.worldofcinema.utils.AppConstants.IN_THEATERS_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.MOST_POPULAR_CATEGORY_ID
import app.worldofcinema.utils.AppConstants.MOST_POPULAR_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.TOP_MOVIES_CATEGORY_ID
import app.worldofcinema.utils.AppConstants.TOP_MOVIES_CATEGORY_TITLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesDAO: MoviesDAO,
) : MoviesRepository {

    override suspend fun getAllMovies() {
        coroutineScope() {
            val topMovies = async { getTopMovies() }
            val mostPopular = async { getMostPopularMovies() }
            val inTheater = async { getInTheatersMovies() }
            val comingSoon = async { getComingSoonMovies() }

            topMovies.await()
            mostPopular.await()
            inTheater.await()
            comingSoon.await()
        }
    }

    override suspend fun getTopMovies() {
        val category = CategoriesEntity(TOP_MOVIES_CATEGORY_ID, TOP_MOVIES_CATEGORY_TITLE)
        getMoviesFor(category)
    }

    override suspend fun getMostPopularMovies() {
        val category = CategoriesEntity(MOST_POPULAR_CATEGORY_ID, MOST_POPULAR_CATEGORY_TITLE)
        getMoviesFor(category)
    }

    override suspend fun getInTheatersMovies() {
        val category = CategoriesEntity(IN_THEATERS_CATEGORY_ID, IN_THEATERS_CATEGORY_TITLE)
        getMoviesFor(category)
    }

    override suspend fun getComingSoonMovies() {
        val category = CategoriesEntity(COMING_SOON_CATEGORY_ID, COMING_SOON_CATEGORY_TITLE)
        getMoviesFor(category)
    }

    private suspend fun getMoviesFor(category: CategoriesEntity) {
        return withContext(Dispatchers.IO) {
            if (!moviesDAO.doesMovieEntityExist()) {
                val response: Response<MoviesResponse>? = when (category.id) {
                    TOP_MOVIES_CATEGORY_ID -> apiService.getTopMovies()
                    MOST_POPULAR_CATEGORY_ID -> apiService.getMostPopularMovies()
                    IN_THEATERS_CATEGORY_ID -> apiService.getInTheatersMovies()
                    COMING_SOON_CATEGORY_ID -> apiService.getComingSoonMovies()
                    else -> {
                        null
                    }
                }

                response?.body()?.items?.let { listMovies ->
                    moviesDAO.insertCategoryEntity(category)
                    val shortTopMoviesList = listMovies.take(10)
                    shortTopMoviesList.map {
                        val moviesEntity =
                            MoviesEntity(it.id,
                                category.id,
                                it.imDbRating,
                                it.image,
                                it.title,
                                it.year)
                        moviesDAO.insertMoviesEntity(moviesEntity)
                    }
                }
            }
        }
    }

    override suspend fun showAllMovies(): List<Category> {
        return withContext(Dispatchers.IO) {
            val categories = mutableListOf<Category>()
            val categoryEntities = moviesDAO.getCategoriesEntities()
            categoryEntities.forEach { category ->
                val movies = moviesDAO.getMoviesEntities(category.id).map { movie ->
                    MoviesModel(movieEntity = movie)
                }
                categories.add(Category(
                    category.categoryTitle,
                    movies
                ))
            }
            categories
        }
    }
}