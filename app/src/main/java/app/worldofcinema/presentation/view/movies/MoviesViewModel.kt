package app.worldofcinema.presentation.view.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.domain.movies.MoviesInteractor
import app.worldofcinema.presentation.view.movies.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesInteractor: MoviesInteractor,
) : ViewModel() {

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getData() {
        viewModelScope.launch {
            try {
                val fetch = async { moviesInteractor.getAllMovies() }
                fetch.await()
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    fun showAllMovies() {
        viewModelScope.launch {
            try {
                val listMovies = moviesInteractor.showAllMovies()
                _items.value = listMovies
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}