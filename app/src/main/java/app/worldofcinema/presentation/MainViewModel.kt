package app.worldofcinema.presentation

import androidx.lifecycle.ViewModel
import app.worldofcinema.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : ViewModel() {


}