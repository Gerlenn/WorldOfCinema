package app.worldofcinema.presentation.view.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.worldofcinema.R
import app.worldofcinema.domain.auth.AuthInteractor
import app.worldofcinema.utils.InternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val COROUTINE_NAME = "with exception"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val context: Context,
) : ViewModel() {

    private lateinit var check: InternetConnection

    private val _navigation = MutableLiveData<Int?>()
    val navigation: LiveData<Int?> = _navigation

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    fun loginUser(userName: String, userPassword: String) {

        check = InternetConnection(context)

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
            _error.value = R.string.exceptionHandler_called
        }

        viewModelScope.launch(CoroutineName(COROUTINE_NAME) + Dispatchers.Main + coroutineExceptionHandler) {
            try {
                launch {
                    if (check.isOnline()){
                        authInteractor.loginUser(userName, userPassword)
                        _navigation.value = R.navigation.main_graph
                    }else{
                        _error.value = R.string.errorInternet
                    }
                }
            } catch (e: Exception) {
                _error.value = R.string.error_login_user
            }
        }
    }

    fun validateCredentials(username: String, password: String) {
        if (!isValidUsername(username)) {
            _loginState.value = LoginState.Error(R.string.invalid_username)
        } else if (!isValidPassword(password)) {
            _loginState.value = LoginState.Error(R.string.invalid_password)
        } else {
            _loginState.value = LoginState.Success
        }
    }

    private fun isValidUsername(username: String): Boolean {
        return !(username.isEmpty() || username.length > 10 || username.length < 3)
    }

    private fun isValidPassword(password: String): Boolean {
        return !(password.isEmpty() || password.length > 10 || password.length < 6)
    }

    fun userNavigated() {
        _navigation.value = null
    }
}

sealed class LoginState {
    object Success : LoginState()
    data class Error(val messageResId: Int) : LoginState()
}