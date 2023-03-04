package app.worldofcinema.data.movies

import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieDetailsRepository
import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsById(id: String): DetailsModel {
        return withContext(Dispatchers.IO) {
            val response = apiService.getDetailsMovieById(id)
            val details = response.body()?.let {
                DetailsModel(
                    detailsResponse = it
                )
            }
            details!!
        }
    }
}