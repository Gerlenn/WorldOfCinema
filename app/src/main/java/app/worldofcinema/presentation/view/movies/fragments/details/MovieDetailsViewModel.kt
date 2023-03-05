package app.worldofcinema.presentation.view.movies.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.domain.movies.MovieDetailsInteractor
import app.worldofcinema.presentation.view.movies.model.detailsfragment.DetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsInteractor: MovieDetailsInteractor,
) : ViewModel() {

    private val _movie = MutableLiveData<DetailsModel>()
    val movie: LiveData<DetailsModel> = _movie

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getMovieDetailsById(id: String) {
        viewModelScope.launch {
            try {
                val movieDetails = movieDetailsInteractor.getMovieDetailsById(id)
                _movie.value = movieDetails
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}