package app.worldofcinema.data.movies

import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieSearchRepository
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieSearchRepository {

    override suspend fun showSearchMovies(searchText: String): List<ResultSearchModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.searchMovies(searchText)
            response.body()?.results?.let { listSearchMovies ->
                val shortTopMoviesList = listSearchMovies.take(7)
                shortTopMoviesList.map { movie ->
                    ResultSearchModel(result = movie)
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}
