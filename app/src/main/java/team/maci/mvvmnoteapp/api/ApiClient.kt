package team.maci.mvvmnoteapp.api

import android.app.AuthenticationRequiredException
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import team.maci.mvvmnoteapp.api.request.AuthRequest
import team.maci.mvvmnoteapp.api.response.TokenResponse
import team.maci.mvvmnoteapp.manager.PreferenceManager
import team.maci.mvvmnoteapp.model.BaseNote
import team.maci.mvvmnoteapp.model.Note
import javax.inject.Inject

class ApiClient {
    private val apiInterface: ApiInterface
    private val preferenceManager: PreferenceManager

    @Inject
    public constructor(retrofit: Retrofit, preferenceManager: PreferenceManager) {
        apiInterface = retrofit.create(ApiInterface::class.java)
        this.preferenceManager = preferenceManager
    }

    fun login(username: String, password: String): Deferred<TokenResponse> {
        return apiInterface.login(AuthRequest(username, password))
    }

    fun register(username: String, password: String): Deferred<TokenResponse> {
        return apiInterface.register(AuthRequest(username, password))
    }

    fun getNotes(token: String): Deferred<List<Note>> {
        return apiInterface.getNotes(token)
    }

    fun createNote(token: String, note: BaseNote) : Deferred<Note>{
        return apiInterface.createNote(token, note)
    }

    fun updateNote(token: String, note: BaseNote) : Deferred<Note>{
        return apiInterface.updateNote(token, note.id, note)
    }

    fun deleteNote(token: String, note: BaseNote) : Deferred<Void>{
        return apiInterface.deleteNote(token, note.id)
    }


}