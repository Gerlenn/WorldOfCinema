package app.worldofcinema.presentation.view.movies.fragments.detailsmovie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieDetailsInteractor
import app.worldofcinema.domain.movies.MovieFavoritesInteractor
import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.utils.NavigateWithActorParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsInteractor: MovieDetailsInteractor,
    private val movieFavoritesInteractor: MovieFavoritesInteractor,
    private val context: Context
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _movie = MutableLiveData<DetailsModel>()
    val movie: LiveData<DetailsModel> = _movie

    private val _navigate = MutableLiveData<NavigateWithActorParam?>()
    val navigate: LiveData<NavigateWithActorParam?> = _navigate

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundle = MutableLiveData<NavigateWithActorParam?>()
    val bundle: LiveData<NavigateWithActorParam?> = _bundle


    fun getMovieDetailsById(id: String) {
        viewModelScope.launch {
            try {
                val movieDetails = movieDetailsInteractor.getMovieDetailsById(id)
                _movie.value = movieDetails
            } catch (e: Exception) {
                _error.value = R.string.error_get_movie_details
            }
        }
    }

    fun favoriteSelected(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                movieDetailsInteractor.favoriteSelected(id, isFavorite)
            } catch (e: Exception) {
                _error.value = R.string.error_fav_selected
            }
        }
    }

    fun onAllActorSelected(movieId: String, movieTitle: String) {
        check = InternetConnection(context)
        viewModelScope.launch {
            try {
                if (check.isOnline()) {
                    _navigate.value = NavigateWithActorParam(
                        movieId, movieTitle, R.id.action_movieDetailsFragment_to_fullActorsFragment
                    )
                } else {
                    _error.value = R.string.errorInternet
                }
            } catch (e: Exception) {
                _error.value = R.string.error_select_category
            }
        }
    }

    fun userNavigatedWithActorParam() {
        _navigate.value = null
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            try {
                movieFavoritesInteractor.deleteFavItemById(id)
            } catch (e: Exception) {
                _error.value = R.string.error_fav_delete
            }
        }
    }
}