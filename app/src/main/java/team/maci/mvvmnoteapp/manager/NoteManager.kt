package team.maci.mvvmnoteapp.manager

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.api.ApiClient
import team.maci.mvvmnoteapp.model.BaseNote
import team.maci.mvvmnoteapp.model.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteManager @Inject constructor(
    private val apiClient: ApiClient,
    private val authService: AuthService
) {


    private lateinit var notes: ArrayList<Note>
    private var loadingJob: Job? = null
    suspend fun getNotes(): List<Note> {
        loadingJob?.join()
        return notes
    }


    suspend fun loadNotes() {
        loadingJob = GlobalScope.launch {
            val loadedNotes = apiClient.getNotes(authService.getToken()).await()
            notes = ArrayList(loadedNotes)
        }
        loadingJob?.join()
    }

    fun getNote(id: Int) = notes.find {
        it.id == id
    }

    suspend fun createNote(title: String, description: String) : Note{
        val noteRequest = BaseNote(0, title, description)
        val createdNote = this.apiClient.createNote(
            this.authService.getToken(),
            noteRequest
        ).await()
        notes.add(createdNote)

        return createdNote
    }

    suspend fun updateNote(id: Int, title: String, description: String) : Pair<Int, Note>?{
        val noteRequest = BaseNote(id, title, description)
        val updatedNote = apiClient.updateNote(
            authService.getToken(),
            noteRequest
        ).await()

        var updatedIndex = notes.indexOfFirst { it.id == id }
        if(updatedIndex >= 0){
            notes[updatedIndex] = updatedNote
            return Pair(updatedIndex,updatedNote)
        }

        return null
    }

    suspend fun deleteNote(note: Note) : Int{
        apiClient.deleteNote(
            authService.getToken(),
            note
        ).await()

        val removedIndex = notes.indexOfFirst {it.id == note.id}

        if(removedIndex >= 0){
            notes.removeAt(removedIndex)
            return removedIndex
        }

        return -1
    }


}