package app.worldofcinema.presentation.view.movies.fragments.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MoviesInteractor
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.presentation.view.movies.model.moviesfragment.Category
import app.worldofcinema.utils.NavigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesInteractor: MoviesInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    private val _showId = MutableLiveData<String>()
    val showId: LiveData<String> = _showId

    private val _bundle = MutableLiveData<NavigateWithId?>()
    val bundle: LiveData<NavigateWithId?> = _bundle

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    fun getData() {
        viewModelScope.launch {
            try {
                val fetch = async { moviesInteractor.getAllMovies() }
                fetch.await()
            } catch (e: Exception) {
                _error.value = R.string.error_get_movies
            }
        }
    }

    fun showAllMovies() {
        viewModelScope.launch {
            try {
                val listMovies = moviesInteractor.showAllMovies()
                _items.value = listMovies
            } catch (e: Exception) {
                _error.value = R.string.error_show_movies
            }
        }
    }

    fun onMovieSelected(id: String) {
        check = InternetConnection(context)
        try {
            if (check.isOnline()) {
                _bundle.value = NavigateWithId(
                    R.id.action_moviesFragment_to_movieDetailsFragment, id)
            } else {
                _error.value = R.string.errorInternet
            }
        } catch (e: Exception) {
            _error.value = R.string.error_select_movies
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }
}