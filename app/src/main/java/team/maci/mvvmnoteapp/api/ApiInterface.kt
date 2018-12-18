package team.maci.mvvmnoteapp.api


import android.provider.ContactsContract
import kotlinx.coroutines.Deferred

import retrofit2.http.*
import team.maci.mvvmnoteapp.api.request.AuthRequest
import team.maci.mvvmnoteapp.api.response.TokenResponse
import team.maci.mvvmnoteapp.model.BaseNote
import team.maci.mvvmnoteapp.model.Note

interface ApiInterface{
    @POST("login/")
    fun login(@Body request: AuthRequest) : Deferred<TokenResponse>


    @POST("register/")
    fun register(@Body request: AuthRequest) : Deferred<TokenResponse>

    @GET("notes/")
    fun getNotes(@Header("Authorization") token: String) : Deferred<List<Note>>

    @PUT("note/")
    fun createNote(@Header("Authorization") token: String, @Body note: BaseNote) : Deferred<Note>

    @POST("note/{id}/")
    fun updateNote(@Header("Authorization") token: String, @Path("id") noteId: Int, @Body note: BaseNote) : Deferred<Note>

    @DELETE("note/{id}/")
    fun deleteNote(@Header("Authorization") token: String, @Path("id") noteId: Int) : Deferred<Void>

}