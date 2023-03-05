package app.worldofcinema.presentation.view.movies.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.movies.MovieSearchInteractor
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieSearchInteractor: MovieSearchInteractor,
) : ViewModel() {

    private val _movies = MutableLiveData<List<ResultSearchModel>>()
    val movies: LiveData<List<ResultSearchModel>> = _movies

    private val _shortSearch = MutableLiveData<Int>()
    val shortSearch: LiveData<Int> = _shortSearch

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    fun findIMovies(searchText: String) {
        viewModelScope.launch {
            try {
                if (searchText.length > 2){
                    val foundMovies = movieSearchInteractor.showSearchMovies(searchText)
                    _movies.value = foundMovies
                }else{
                    _shortSearch.value = R.string.short_input
                }
            } catch (e: Exception) {
                _error.value = R.string.short_input
            }
        }
    }
}

