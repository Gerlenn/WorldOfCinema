package app.worldofcinema.presentation.view.movies.fragments.main.allmoviescategory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieCategoryInteractor
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.utils.NavigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMoviesViewModel @Inject constructor(
    private val movieCategoryInteractor: MovieCategoryInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _listMovie = MutableLiveData<List<MoviesModel>>()
    val listMovie: LiveData<List<MoviesModel>> = _listMovie

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundle = MutableLiveData<NavigateWithId?>()
    val bundle: LiveData<NavigateWithId?> = _bundle

    fun showCategoryMovies(searchText: String) {
        check = InternetConnection(context)
        viewModelScope.launch {
            try {
                if (check.isOnline()) {
                    val listMovies = movieCategoryInteractor.showCategoryMovies(searchText)
                    _listMovie.value = listMovies
                } else {
                    _error.value = R.string.errorInternet
                }
            } catch (e: Exception) {
                _error.value = R.string.error_get_category_movies
            }
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }

    fun onMovieSelected(id: String) {
        if (check.isOnline()) {
            _bundle.value =
                NavigateWithId(R.id.action_categoryMoviesFragment_to_movieDetailsFragment, id)
        } else {
            _error.value = R.string.errorInternet
        }
    }
}