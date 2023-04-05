package app.worldofcinema.presentation.view.movies.fragments.allactors

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieActorInteractor
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor
import app.worldofcinema.utils.InternetConnection
import app.worldofcinema.utils.NavigateWithActorParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullActorsFragmentViewModel @Inject constructor(
    private val movieActorInteractor: MovieActorInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _listActors = MutableLiveData<List<Actor>>()
    val listActors: LiveData<List<Actor>> = _listActors

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _bundle = MutableLiveData<NavigateWithActorParam?>()
    val bundle: LiveData<NavigateWithActorParam?> = _bundle

    fun showActorsMovies(movieId: String) {
        check = InternetConnection(context)
        viewModelScope.launch {
            try {
                if (check.isOnline()) {
                    val listMovies = movieActorInteractor.showActorMovies(movieId)
                    _listActors.value = listMovies
                } else {
                    _error.value = R.string.errorInternet
                }
            } catch (e: Exception) {
                _error.value = R.string.error_get_actors_movie
            }
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }
}