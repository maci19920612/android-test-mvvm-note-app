package team.maci.mvvmnoteapp.manager

import kotlinx.coroutines.delay
import team.maci.mvvmnoteapp.api.ApiClient


class AuthService constructor(
    private val preferenceManager: PreferenceManager,
    private val apiClient: ApiClient
){
    suspend fun login(username: String, password: String) {
        delay(5000)
        val tokenResponse = apiClient.login(username, password).await()
        preferenceManager.setToken(tokenResponse.token)
    }

    suspend fun register(username: String, password: String) {
        delay(2000)
        val token = apiClient.register(username, password).await()
        preferenceManager.setToken(token.token)
    }

    fun getToken() = preferenceManager.getToken() ?: throw MissingTokenException()

    fun isLoggedIn() = preferenceManager.hasToken()
}

class MissingTokenException : RuntimeException("You have to login before obtain token")