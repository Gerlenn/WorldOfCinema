package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel

interface MovieSearchRepository {

    suspend fun showSearchMovies(searchText: String): List<ResultSearchModel>
}