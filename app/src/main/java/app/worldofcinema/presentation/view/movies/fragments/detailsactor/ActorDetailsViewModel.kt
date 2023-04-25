package app.worldofcinema.presentation.view.movies.fragments.detailsactor

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieActorDetailsInteractor
import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.ActorDetailsModel
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.utils.NavigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel @Inject constructor(
    private val movieActorDetailsInteractor: MovieActorDetailsInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _actor = MutableLiveData<ActorDetailsModel>()
    val actor: LiveData<ActorDetailsModel> = _actor

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundleMovie = MutableLiveData<NavigateWithId?>()
    val bundleMovie: LiveData<NavigateWithId?> = _bundleMovie

    fun getActorDetailsById(actorId: String) {
        check = InternetConnection(context)
        viewModelScope.launch {
            try {
                if (check.isOnline()) {
                    val actorDetails = movieActorDetailsInteractor.getActorDetailsById(actorId)
                    _actor.value = actorDetails
                } else {
                    _error.value = R.string.errorInternet
                }
            } catch (e: Exception) {
                _error.value = R.string.error_get_actor_details
            }
        }
    }

    fun onMovieSelected(id: String) {
        check = InternetConnection(context)
        try {
            if (check.isOnline()) {
                _bundleMovie.value = NavigateWithId(
                    R.id.action_actorDetailsFragment_to_movieDetailsFragment, id
                )
            } else {
                _error.value = R.string.errorInternet
            }
        } catch (e: Exception) {
            _error.value = R.string.error_select_movies
        }
    }

    fun userNavigated() {
        _bundleMovie.value = null
    }
}