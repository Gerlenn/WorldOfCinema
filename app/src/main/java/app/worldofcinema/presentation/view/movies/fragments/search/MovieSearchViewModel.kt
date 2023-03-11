package app.worldofcinema.presentation.view.movies.fragments.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieSearchInteractor
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import app.worldofcinema.utils.NavigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieSearchInteractor: MovieSearchInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _movies = MutableLiveData<List<ResultSearchModel>>()
    val movies: LiveData<List<ResultSearchModel>> = _movies

    private val _shortSearch = MutableLiveData<Int>()
    val shortSearch: LiveData<Int> = _shortSearch

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundle = MutableLiveData<NavigateWithId?>()
    val bundle: LiveData<NavigateWithId?> = _bundle


    fun findIMovies(searchText: String) {
        check = InternetConnection(context)
        viewModelScope.launch {
            try {
                if (check.isOnline()) {
                    if (searchText.length > 2) {
                        val foundMovies = movieSearchInteractor.showSearchMovies(searchText)
                        _movies.value = foundMovies
                    } else if (searchText.length == 1) {
                        _shortSearch.value = R.string.short_input
                    }
                } else {
                    _error.value = R.string.errorInternet
                }
            } catch (e: Exception) {
                _error.value = R.string.nothing_fount
            }
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }

    fun onMovieSelected(id: String) {
        if (check.isOnline()) {
            _bundle.value =
                NavigateWithId(R.id.action_movieSearchFragment_to_movieDetailsFragment, id)
        } else {
            _error.value = R.string.errorInternet
        }
    }
}

