package app.worldofcinema.data.movies

import app.worldofcinema.data.database.entities.MoviesEntity
import app.worldofcinema.data.model.moviesesponse.MoviesResponse
import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieCategoryRepository
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import app.worldofcinema.utils.AppConstants
import app.worldofcinema.utils.AppConstants.COMING_SOON_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.IN_THEATERS_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.MOST_POPULAR_CATEGORY_TITLE
import app.worldofcinema.utils.AppConstants.TOP_MOVIES_CATEGORY_TITLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieCategoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieCategoryRepository {

    override suspend fun showCategoryMovies(categoryTitle: String): List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<MoviesResponse>? = when (categoryTitle) {
                TOP_MOVIES_CATEGORY_TITLE -> apiService.getTopMovies()
                MOST_POPULAR_CATEGORY_TITLE -> apiService.getMostPopularMovies()
                IN_THEATERS_CATEGORY_TITLE -> apiService.getInTheatersMovies()
                COMING_SOON_CATEGORY_TITLE -> apiService.getComingSoonMovies()
                else -> {
                    null
                }
            }

            response?.body()?.items?.let { listMovies ->
                listMovies.map { movie ->
                    MoviesModel(
                        movie.id,
                        movie.imDbRating,
                        movie.image,
                        movie.title,
                        movie.year)
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}