package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import app.worldofcinema.presentation.view.movies.model.searchfragment.SearchModel
import javax.inject.Inject

class MovieSearchInteractor @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository,
) {

    suspend fun showSearchMovies(searchText: String): List<ResultSearchModel> {
        return movieSearchRepository.showSearchMovies(searchText)
    }
}