package app.worldofcinema.presentation.view.movies.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieDetailsInteractor
import app.worldofcinema.domain.movies.MovieFavoritesInteractor
import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsInteractor: MovieDetailsInteractor,
    private val movieFavoritesInteractor: MovieFavoritesInteractor
) : ViewModel() {

    private val _movie = MutableLiveData<DetailsModel>()
    val movie: LiveData<DetailsModel> = _movie

    private val _errorGetMovieDetails = MutableLiveData<Int>()
    val errorGetMovieDetails: LiveData<Int> = _errorGetMovieDetails

    private val _errorFavSelected = MutableLiveData<Int>()
    val errorFavSelected: LiveData<Int> = _errorFavSelected

    fun getMovieDetailsById(id: String) {
        viewModelScope.launch {
            try {
                val movieDetails = movieDetailsInteractor.getMovieDetailsById(id)
                _movie.value = movieDetails
            } catch (e: Exception) {
                _errorGetMovieDetails.value = R.string.error_get_movie_details
            }
        }
    }

    fun favoriteSelected(id: String, isFavorite: Boolean){
        viewModelScope.launch {
            try {
               movieDetailsInteractor.favoriteSelected(id, isFavorite)
            } catch (e: Exception) {
                _errorFavSelected.value = R.string.error_fav_selected
            }
        }
    }

    fun deleteFavorite(id: String){
        viewModelScope.launch {
            try {
                movieFavoritesInteractor.deleteFavItemById(id)
            } catch (e: Exception) {
                _errorFavSelected.value = R.string.error_fav_selected
            }
        }
    }
}