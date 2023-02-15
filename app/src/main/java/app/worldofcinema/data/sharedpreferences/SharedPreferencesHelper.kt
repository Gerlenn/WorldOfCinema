package app.worldofcinema.data.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    fun saveUserName(name: String?) {
        sharedPreferences.edit().putString(USER_NAME, name).apply()
    }

    fun saveUserPassword(password: String?) {
        sharedPreferences.edit().putString(USER_PASSWORD, password).apply()
    }

    fun checkUserExists(): Boolean {
        val name = sharedPreferences.getString(USER_NAME, "")
        val password = sharedPreferences.getString(USER_PASSWORD, "")
        return (!name.isNullOrEmpty() && !password.isNullOrEmpty())
    }

    companion object {
        private const val USER_NAME = "USER NAME"
        private const val USER_PASSWORD = "USER PASSWORD"
    }
}