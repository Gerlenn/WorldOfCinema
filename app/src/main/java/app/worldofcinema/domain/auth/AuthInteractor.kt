package app.worldofcinema.domain.auth

import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend fun loginUser(userName: String, userPassword: String) {
        authRepository.loginUser(userName, userPassword)
    }

    suspend fun checkUserExists(): Boolean {
        return authRepository.doesUserExist()
    }
}