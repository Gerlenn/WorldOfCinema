package app.worldofcinema.presentation.view.movies.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieFavoritesInteractor
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoritesViewModel @Inject constructor(
    private val movieFavoritesInteractor: MovieFavoritesInteractor,
) : ViewModel() {

    private val _favoritesMovies = MutableLiveData<List<FavoritesModel>>()
    val favoritesMovies = _favoritesMovies

    private val _errorGetFav = MutableLiveData<Int>()
    val errorGetFav: LiveData<Int> = _errorGetFav

    private val _errorDelFav = MutableLiveData<Int>()
    val errorDelFav: LiveData<Int> = _errorDelFav

    private val _logoutUser = MutableLiveData<Int?>()
    val logoutUser: LiveData<Int?> = _logoutUser

    fun getFavorites() {
        viewModelScope.launch {
            try {
                val favoritesItems = movieFavoritesInteractor.getFavorites()
                favoritesItems.collect { listFavorites ->
                    _favoritesMovies.value = listFavorites
                }
            } catch (e: Exception) {
                _errorGetFav.value = R.string.get_favorite_error
            }
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            try {
                movieFavoritesInteractor.deleteFavItemById(id)
            } catch (e: Exception) {
                _errorDelFav.value = R.string.delete_favorite_error
            }
        }
    }

    fun logOutUser(){
        viewModelScope.launch {
            movieFavoritesInteractor.logoutUser()
            _logoutUser.value = R.navigation.auth_graph
        }
    }
}