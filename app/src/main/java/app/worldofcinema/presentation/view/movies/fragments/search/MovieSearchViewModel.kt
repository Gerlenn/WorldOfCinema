package app.worldofcinema.presentation.view.movies.fragments.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieSearchInteractor
import app.worldofcinema.presentation.view.movies.fragments.main.NavigateWithId
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieSearchInteractor: MovieSearchInteractor,
) : ViewModel() {

    private val _movies = MutableLiveData<List<ResultSearchModel>>()
    val movies: LiveData<List<ResultSearchModel>> = _movies

    private val _shortSearch = MutableLiveData<Int>()
    val shortSearch: LiveData<Int> = _shortSearch

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundle = MutableLiveData<NavigateWithId?>()
    val bundle: LiveData<NavigateWithId?> = _bundle


    fun findIMovies(searchText: String) {
        viewModelScope.launch {
            try {
                if (searchText.length > 2){
                    val foundMovies = movieSearchInteractor.showSearchMovies(searchText)
                    _movies.value = foundMovies
                }else if (searchText.length == 1) {
                    _shortSearch.value = R.string.short_input
                }
            } catch (e: Exception) {
                _error.value = R.string.short_input
            }
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }

    fun onMovieSelected(id: String) {

        _bundle.value = NavigateWithId(
            R.id.action_movieSearchFragment_to_movieDetailsFragment, id

        )
        Log.w("ID_FILM!!!", "$id")
    }
}

