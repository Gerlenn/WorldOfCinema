package app.worldofcinema.presentation.view.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : ViewModel() {

    private val _navigation = MutableLiveData<Int?>()
    val navigation: LiveData<Int?> = _navigation


    fun loginUser(userName: String, userPassword: String) {

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            Log.w("exceptionHandler called", exception.toString())
        }

        viewModelScope.launch(CoroutineName("with exception") + Dispatchers.Main + coroutineExceptionHandler) {
            try {
                launch {
                    authInteractor.loginUser(userName, userPassword)
                    _navigation.value = R.navigation.main_graph
                }
            } catch (e: Exception) {
                Log.w("exception", "loginUser FAILED")
            }
        }
    }

    fun userNavigated() {
        _navigation.value = null
    }
}