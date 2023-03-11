package app.worldofcinema.presentation.view.movies.fragments.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieFavoritesInteractor
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import app.worldofcinema.utils.NavigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoritesViewModel @Inject constructor(
    private val movieFavoritesInteractor: MovieFavoritesInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _favoritesMovies = MutableLiveData<List<FavoritesModel>>()
    val favoritesMovies = _favoritesMovies

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _logoutUser = MutableLiveData<Int?>()
    val logoutUser: LiveData<Int?> = _logoutUser

    private val _bundle = MutableLiveData<NavigateWithId?>()
    val bundle: LiveData<NavigateWithId?> = _bundle

    fun getFavorites() {
        viewModelScope.launch {
            try {
                val favoritesItems = movieFavoritesInteractor.getFavorites()
                favoritesItems.collect { listFavorites ->
                    _favoritesMovies.value = listFavorites
                }
            } catch (e: Exception) {
                _error.value = R.string.get_favorite_error
            }
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            try {
                movieFavoritesInteractor.deleteFavItemById(id)
            } catch (e: Exception) {
                _error.value = R.string.delete_favorite_error
            }
        }
    }

    fun logOutUser() {
        viewModelScope.launch {
            movieFavoritesInteractor.logoutUser()
            _logoutUser.value = R.navigation.auth_graph

        }
    }

    fun userNavigated() {
        _bundle.value = null
    }

    fun onMovieSelected(id: String) {
        check = InternetConnection(context)
        if (check.isOnline()) {
            _bundle.value = NavigateWithId(
                R.id.action_movieFavoritesFragment_to_movieDetailsFragment, id
            )
        } else {
            _error.value = R.string.errorInternet
        }
    }
}