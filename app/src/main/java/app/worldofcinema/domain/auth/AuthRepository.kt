package app.worldofcinema.domain.auth

interface AuthRepository {

    suspend fun loginUser(userName: String, userPassword: String)

    suspend fun doesUserExist(): Boolean

}